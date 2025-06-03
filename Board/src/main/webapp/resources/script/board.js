function boardCheck() {
   if (document.frm.name.value.length == 0) {
      alert("작성자를 입력하세요.");
      return false;
   }
   if (document.frm.pass.value.length == 0) {
      alert("비밀번호를 입력하세요.");
      return false;
   }
   if (document.frm.title.value.length == 0) {
      alert("제목을 입력하세요.");
      return false;
   }
   return true;
}
// open_win('BoardServlet?command=board_check_pass_form&num=${board.num}', 'delete') 삭제할 때 나오는 유효성 검사
function open_win(url, name) {
   window.open(url, name, "width=500, height=230"); // 새창 띄우기
}
function passCheck() {
   if (document.frm.pass.value.length == 0) {
      alert("비밀번호를 입력하세요.");
      return false;
   }
   return true;
}