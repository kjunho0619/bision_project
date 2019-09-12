<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일일 가계부</title>
<script src='<c:url value="/js/jquery-3.4.1.js" />'></script>
</head>
<script>
	var inputdate;
	var type;
	var amount;

	function inputDateChange() {
		inputdate = $('#inputdate').val();
		type = $('#type').val();
		$
				.ajax({
					url : 'selectData',
					type : 'post',
					data : {
						"inputdate" : inputdate,
						"type" : type
					},
					success : function(result) {
						$('#moneybookTable').empty();
						$('#moneybookTable').append(
								"<tr>" + "<th><h1>메모</h1></th>"
										+ "<th><h1>금액</h1></th>"
										+ "<th><h1>삭제</h1></th>" + "</tr>");

						$(result)
								.each(
										function(index, item) {
											$('#moneybookTable')
													.append(
															"<tr style='text-align: center;'>"
																	+ "<td>"
																	+ item.memo
																	+ "</td>"
																	+ "<td>"
																	+ item.amount
																	+ "</td>"
																	+ "<td><input type='radio' id='deleteRadio' name='deleteRadio' value='"+ item.infonum +"'></td>"
																	+ "</tr>");
										});

					},
					error : function() {
						alert("애러발생");
					}
				});
	}

	function insertData() {
		inputdate = $('#inputdate').val();
		memo = $('#memo').val();
		amount = $('#amount').val();

		if (inputdate == '' || inputdate == null) {
			alert("날짜를 입력하세요.");
			return false;
		}

		if (memo == '' || memo == null) {
			alert("메모를 입력하세요.");
			return false;
		}

		if (amount == '' || amount == null) {
			alert("금액를 입력하세요.");
			return false;
		}

		if (isNaN(amount) == true) {
			alert("숫자를 입력하세요.");
			return false;
		}

		$.ajax({
			url : 'insertData',
			type : 'post',
			data : $('#moneybookForm').serialize(),
			success : function(result) {
				if (result == true) {
					alert("등록 성공");
					inputDateChange();
					$(".textbox").val("");
				} else {
					alert("등록 실패");
					return false;
				}
			},
			error : function() {
				alert("애러발생");
				return false;
			}
		});
	}

	function deleteData(item) {
		/*  		alert( "input[name='deleteRadio'] : " + $("input[name='deleteRadio']").is(":checked") );
		 alert( "input[name='deleteRadio']:checked : " + $("input[name='deleteRadio']:checked").val() ); */

		var checkedYN = $("input[name='deleteRadio']").is(":checked");
		var infonum = $("input[name='deleteRadio']:checked").val();

		if (checkedYN == false) {
			alert("삭제할 항목을 선택해주세요.");
			return false;
		}

		$.ajax({
			url : 'deleteData',
			type : 'post',
			data : {"infonum" : infonum },
			success : function(result) {
				if (result == true) {
					alert("삭제 성공");
					inputDateChange();
				} else {
					alert("삭제 실패");
					return false;
				}
			},
			error : function() {
				alert("애러발생");
				return false;
			}
		});
	}
</script>
<body>
	<div>
		<form id="moneybookForm">
			<select id="type" onchange="inputDateChange()" name="type">
				<option value="수입" selected="selected">수입</option>
				<option value="지출">지출</option>
			</select> <input type="date" id="inputdate" name="inputdate"
				onchange="inputDateChange()"><br>
			<div style="width: 650px; text-align: center;">
				<h1>메모&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
					&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp 금액</h1>
			</div>
			<br> <input type="text" id="memo" name="memo"
				placeholder="내용 입력" class="textbox" style="width: 330px;"> <input
				type="text" id="amount" name="amount" placeholder="금액 입력"
				class="textbox" style="width: 330px;"> <input type="button"
				id="insertButton" value="등록" onclick="insertData()"
				style="width: 55px;"> <input type="button" id="deleteButton"
				value="삭제" onclick="deleteData()" style="width: 55px;">
		</form>
	</div>
	<hr>
	<div>
		<table id="moneybookTable"	style="border: solid 3px red; width: 800px;">
			<tr>
				<th><h1>메모</h1></th>
				<th><h1>금액</h1></th>
			</tr>
		</table>
		<br>
	</div>
</body>
</html>
