<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<style type="text/css">
#add-form input, #add-form textarea {
	display: block;
	width: 100%;
	margin: 10px;
}

#add-form input {
	height: 25px;
}

input[type='submit'] {
	border: 1px solid #A9A9A9;
	background-color: white;
	cursor: pointer;
}

#add-form textarea {
	height: 150px;
	resize: none;
}

#guestbook h1 {
	height: 35px;
	padding-left: 50px;
	background: url("${pageContext.request.contextPath }/assets/images/guestbook.png") 10px 0 no-repeat;
}

#list-guestbook {
	position: relative;
}

#list-guestbook li {
	margin-bottom: 10px;
	padding: 10px 0;
	display: inline-block;
	position:relative;
}

p {
	width: 90%;
	float: right;
}

.user {
	display:block;
	margin-bottom: 5px;
}

.profile {
	display:block;
	width: 30px;
	height: 30px;
	font-size: 0px;
	float: left;
	background: url("${pageContext.request.contextPath }/assets/images/user.png") 0 0 no-repeat;
}

#list-guestbook a {	
	width: 20px;
	height: 20px;
	background: url("${pageContext.request.contextPath }/assets/images/delete.png") 0 0 no-repeat;
	position:absolute;
    left:20px;
    top:50px;
    text-indent: 4em;
    white-space:nowrap;
    overflow-x:hidden; 
}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/ejs/ejs.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
// jQuery plugin
(function($){
	/* 그냥 변수. 예를 들어 $ 대신 a를 쓴다면 $(this) 대신 a(this)로 사용 */
	// 플러그인 초기화
	$.fn.hello = function (){
		var $element = $(this);
		console.log($element.attr("id") + ":" +":hello~")
	}
})(jQuery);

var isEnd = false;
var ejsListItem = new EJS({
	url:"${pageContext.request.contextPath }/assets/js/ejs/template/listitem.ejs"
});
var messageBox = function(title, message, callback) {
	$("#dialog-message").attr("title", title);
	$("#dialog-message p").text(message);
	
	$( "#dialog-message" ).dialog({
		modal: true,
		buttons: {
			"확인": function() {
				$( this ).dialog( "close" );
			}
		},
		close: callback || function(){
			console.log("close...");
		}
	});
}
var render = function(mode, vo){
	var html = ejsListItem.render(vo);
		/* "<li data-no='"+vo.no+"'>"
		+"<strong class='user'>"+vo.name+"</strong>"
		+"<p>"+vo.content.replace(/\n/gi, "<br>")+"</p>"
		+"<strong class='profile'></strong>"
		+"<a href='#' data-no='"+vo.no+"'>삭제</a>" 
		+"</li>"; */
		if (mode == true) {
			$("#list-guestbook").prepend(html);			
		} else {
			$("#list-guestbook").append(html);
		}
		// 이렇게 해도 됨 (배열로 메소드 이름 호출 가능)
		//$("#list-guestbook")[mode ? "prepend":"append"](html);
}
var fetchList = function(){
	if (isEnd == true) {
		return;
	}
	var startNo = $("#list-guestbook li").last().data("no") || 0;
	console.log(startNo);
	$.ajax({
		url: "/mysite3/api/guestbook/list?no="+startNo,
		type: "get",
		dataType: "json",
		success:function(response){
			// 성공유무
			if (response.result != "success") {
				console.log(response.message);
				return;
			}
			// 끝 감지
			if (response.data.length < 5) {
				isEnd = true;
				$("#btn-fetch").prop("disabled", true);
				//render
			}
			$.each(response.data, function(index, vo){
				render(false, vo);
			});
			/* var length = response.data.length;
			if (length > 0) {
				startNo = response.data[length -1].no;					
			} */
		}
	});
}

$(function(){
	// 삭제시 비밀번호 입력 모탈 다이얼로그
	var deleteDialog = $( "#dialog-delete-form" ).dialog({
      autoOpen: false,
      modal: true,
      buttons: {
        "삭제": function(){
        	console.log("삭제");
        	var password = $("#password-delete").val();
        	var no = $("#hidden-no").val();
        	console.log(password+":"+no);
        	
        	$.ajax({
        		url:"/mysite3/api/guestbook/delete",
        		type: "post",
        		dataType:"json",
        		data: "no="+ no + "&password=" + password,
        		success: function(response){
        			if (response.result == "fail") {
	        			console.log(response.message);
						return;
					}
        			if (response.data == -1) {
						$(".validateTips.normal").hide();
						$(".validateTips.error").show();
						$("#password-delete").val("");
						return;
					}
        			$("#list-guestbook li[data-no="+ response.data + "]").remove();
        			deleteDialog.dialog("close");
        		}
        	})
        },
        "취소": function() {
        	deleteDialog.dialog( "close" );
        }
      },
      close: function() {
    	  console.log("취소");
        /* form[ 0 ].reset();
        allFields.removeClass( "ui-state-error" ); */
        $("#password-delete").val("");
        $("#hidden-no").val("");
        $(".validateTips.normal").show();
		$(".validateTips.error").hide();
      }
    });
	// live Event Listener
	$(document).on("click", "#list-guestbook li a", function(event){
		event.preventDefault();
		
		
		var no = $(this).data("no");
		$("#hidden-no").val(no);
		console.log(no);
		
		deleteDialog.dialog("open");
	});
	$("#add-form").submit(function(event){
		event.preventDefault();
		// .serialize() form의 데이터를 querystring으로 encoding 해줌
		/* var queryString = $(this).serialize();
		console.log(queryString);
		$.ajax({
			url: "/mysite3/api/guestbook/insert",
			type: "post",
			dataType: "json",
			data: queryString,
			success: function(response){
				console.log(response.data);
				render(true, response.data);
				$("add-form")[0].reset();
			}
		}); */
		var data = {};
		$.each($(this).serializeArray(), function(index, o){
			data[o.name] = o.value;
		});
		if (data["name"] == '') {
			messageBox("메세지 등록", "이름이 비어 있습니다.", function(){
				$("#input-name").focus();				
			});
			return;
		}
		if (data["password"] == '') {
			messageBox("메세지 등록", "비밀번호가 비어 있습니다.", function(){
				$("#input-password").focus();
			});
			return;
		}
		if (data["content"] == '') {
			messageBox("메세지 등록", "내용이 비어 있습니다.", function(){
				$("#tx-content").focus();
			});
			return;
		}
		$.ajax({
			url: "/mysite3/api/guestbook/insert",
			type: "post",
			dataType: "json", //받는 타입
			contentType:"application/json", //보내는 타입
			data: JSON.stringify(data),
			success: function(response){
				render(true, response.data);
				$("#add-form")[0].reset();
			}
		});
	});
	$("#btn-fetch").click(function(){
		fetchList();
		/* if (isEnd == true) {
			return;
		}
		var startNo = $("#list-guestbook li").last().data("no") || 0;
		console.log(startNo);
		$.ajax({
			url: "/mysite3/api/guestbook/list?no="+startNo,
			type: "get",
			dataType: "json",
			success:function(response){
				// 성공유무
				if (response.result != "success") {
					console.log(response.message);
					return;
				}
				// 끝 감지
				if (response.data.length < 5) {
					isEnd = true;
					$("#btn-fetch").prop("disabled", true);
					//render
				}
				$.each(response.data, function(index, vo){
					render(false, vo);
				});
				/* var length = response.data.length;
				if (length > 0) {
					startNo = response.data[length -1].no;					
				} */
			/*}
		}); */
	});
	
	$(window).scroll(function(){
		var $window = $(this);
		var scrollTop = $window.scrollTop();
		var windowHeight = $window.height();
		var documentHeight = $(document).height();
		
		console.log(scrollTop+":"+windowHeight+":"+documentHeight);
		
		// scrollbar의 thumb가 바닥 전 30px까지 도달
		if (scrollTop + windowHeight + 30 > documentHeight) {
			fetchList();
		}
	})
	// 최초리스트 가져오기
	fetchList();
	
	// plugin 테스트
	$("#container").hello();
});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<h1>방명록</h1>
				<form id="add-form" action="" method="post">
					<input type="text" id="input-name" name="name" placeholder="이름">
					<input type="password" id="input-password" name="password" placeholder="비밀번호">
					<textarea id="tx-content" name="content" placeholder="내용을 입력해 주세요."></textarea>
					<input type="submit" value="보내기" />
				</form>
				<ul id="list-guestbook">

					<!-- <li data-no=''>
						<strong class="user">지나가다가</strong>
						<p>
							별루입니다.<br>
							비번:1234 -,.-
						</p>
						<strong class="profile">프로필사진</strong>
						<a href='' data-no=''>삭제</a> 
					</li> -->

					
				</ul>
			</div>
			<div id="dialog-delete-form" title="메세지 삭제" style="display:none">
  				<p class="validateTips normal">작성시 입력했던 비밀번호를 입력하세요.</p>
  				<p class="validateTips error" style="display:none">비밀번호가 틀립니다.</p>
  				<form>
 					<input type="password" id="password-delete" value="" class="text ui-widget-content ui-corner-all">
					<input type="hidden" id="hidden-no" value="">
					<input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
  				</form>
			</div>
			<button id="btn-fetch">가져오기</button>
			<div id="dialog-message" title="" style="display:none">
  				<p></p>
			</div>						
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="guestbook-ajax"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>