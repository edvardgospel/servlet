var seconds;
var generatedNumber;
var difficulty;
var userName = "edvard";

function sendInitRequest(difficulty2) {
    $.ajax({
        url: "/difficulty",
        type: "get",
        data: "difficulty="+difficulty2,
        success: function (data) {
                 console.log(data);
                 generatedNumber = data[0].toString();
                 seconds = data[1];
                 startGame();
        },
    });
    difficulty=difficulty2;
}

function startGame() {
    document.getElementById("startButton").disabled = true;
    document.getElementById("selectMenu").disabled = true;
    document.getElementById("input").value = generatedNumber;

    setTimeout(function() {
        document.getElementById("submitButton").disabled = false;
        document.getElementById("input").disabled = false;
        document.getElementById("input").value = "";
    }, seconds * 1000);
}

function submitResult(yourNumber) {
    send(yourNumber);
    document.getElementById("input").value = "";
    document.getElementById("input").disabled = true;
    document.getElementById("startButton").disabled = false;
    document.getElementById("submitButton").disabled = true;
}

function send(yourNumber) {
    var requestObject = {
        userName: userName,
        yourNumber: yourNumber,
        generatedNumber: generatedNumber,
        difficulty: difficulty,
        success: function (data) {
                     console.log("DIS IS GOOD: " + data);
                },
        error: function (data) {
                    console.log("DIS IS SHIT: " + data);
        }
    }
    var requestJson = JSON.stringify(requestObject);

    console.log(requestJson);
    $.ajax({
        url: "/statistics",
        type: "post",
        contentType: "application/json",
        dataType: "json",
        data: requestJson
    });
}

function createColoredNumber(yourNumber,generatedNumber) {
    var coloredNumber = "";
    for (var i=0; i<yourNumber.length; i++) {
        if (yourNumber.charAt(i) === generatedNumber.charAt(i)) {
            coloredNumber += yourNumber.charAt(i);
        } else {
            coloredNumber += yourNumber.charAt(i).fontcolor("red");
        }
    }
    return coloredNumber
}