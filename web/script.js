function getAllMeetings() {

    var x = new XMLHttpRequest();

    x.open("GET", "http://localhost:8080/meetings-service/meetings/full", true);
    //x.setDisableHeaderCheck(true);
    x.onload = function () {
        alert(x.responseText);

    }
    x.send(null);

}
function putMeeting() {
    var x = new XMLHttpRequest();

    x.open("PUT", "http://localhost:8080/meetings-service/meetings/put/" + document.getElementById('title').value + "/" + document.getElementById('summary').value + "/" + document.getElementById('startdate').value + "/" + document.getElementById('enddate').value + "/" + document.getElementById('priority').value, true);

    x.onload = function () {
        alert(x.responseText);
    }
    x.send(null);
}