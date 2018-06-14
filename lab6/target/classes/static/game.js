var seconds;
var generatedNumber;
var difficulty;

function sendInitRequest(initDifficulty) {
    $.ajax({
        url: "/difficulty",
        type: "get",
        data: "difficulty="+initDifficulty,
        success: function (data) {
                 console.log(data);
                 generatedNumber = data.generatedNumber.toString();
                 seconds = data.second;
                 startGame();
        },
    });
    difficulty = initDifficulty;
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
        yourNumber: yourNumber,
        generatedNumber: generatedNumber,
        difficulty: difficulty
    }

    var requestJson = JSON.stringify(requestObject);

    console.log(requestJson);
    $.ajax({
        url: "/statistics",
        type: "post",
        contentType: "application/json",
        dataType: "json",
        data: requestJson,
        success:
            function (data) {
            console.log("NI MI TORTENIUK: " + data.average)
                renderStatistics(data);
            },
        error:
            function (data) {
                console.log("ERROR: " + data);
            }
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
    return coloredNumber;
}

function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}

function renderStatistics(data) {
    $("#generatedNumber").html("Generated number: " + data.generatedNumber);
    $("#yourNumber").html("Your number: " + createColoredNumber(data.yourNumber, data.generatedNumber));
    $("#percentage").html("Percentage: " + data.percentage.toFixed(2));
    $("#"+difficulty+"Average").html(capitalizeFirstLetter(difficulty) + " average: " + data.average.toFixed(2));
    $("#hardestBestResult").html("Hardest best result: " + data.hardestBestResult.toFixed(2));
}