var numSelected = null;
var tileSelected = null;

var errors = 0;

var board = [
    "--74916-5",
    "2---6-3-9",
    "-----7-1-",
    "-586----4",
    "--3----9-",
    "--62--187",
    "9-4-7---2",
    "67-83----",
    "81--45---"
]

var solution = [
    "387491625",
    "241568379",
    "569327418",
    "758619234",
    "123784596",
    "496253187",
    "934176852",
    "675832941",
    "812945763"
]

window.onload = function () {
    setGame();
}

function setGame() {
    // Digits 1-9
    for (let i = 1; i <= 9; i++) {
        //<div id="1" class="number">1</div>
        let number = document.createElement("div");
        number.id = i;
        number.innerText = i;
        number.addEventListener("click", selectNumber);
        number.classList.add("number");
        document.getElementById("digits").appendChild(number);
    }

    // 9x9 Board
    for (let r = 0; r < 9; r++) {
        for (let c = 0; c < 9; c++) {
            let tile = document.createElement("div");
            tile.id = r.toString() + "-" + c.toString();
            if (board[r][c] != "-") {
                tile.innerText = board[r][c];
                tile.classList.add("tile-start");
            }
            if (r == 2 || r == 5) {
                tile.classList.add("horizontal-line");
            }
            if (c == 2 || c == 5) {
                tile.classList.add("vertical-line");
            }
            tile.addEventListener("click", selectTile);
            tile.classList.add("tile");
            document.getElementById("board").append(tile);
        }
    }
}

function selectNumber() {
    if (numSelected != null) {
        numSelected.classList.remove("number-selected");
    }
    numSelected = this;
    numSelected.classList.add("number-selected");
}

function selectTile() {
    if (numSelected) {
        if (this.innerText != "") {
            return;
        }
        let coords = this.id.split("-"); // creates array of two individual numbers
        let r = parseInt(coords[0]);
        let c = parseInt(coords[1]);

        if (solution[r][c] == numSelected.id) {
            this.innerText = numSelected.id;
        } 
        else {
            errors += 1;
            document.getElementById("errors").innerText = "Errors: " + errors;
        }
    }
}

document.addEventListener('DOMContentLoaded', (event) => {
    let timerElement = document.getElementById('timer');
    let intervalId;
    let elapsedTime = 0;
    let boardCompleted = false;

    function updateTimer() {
        if (!boardCompleted) {
            elapsedTime += 1000;
            let minutes = Math.floor(elapsedTime / 60000);
            let seconds = Math.floor((elapsedTime % 60000) / 1000);

            if (seconds < 10) {
                seconds = '0' + seconds;
            }

            timerElement.textContent = `${minutes}:${seconds}`;
        } else {
            clearInterval(intervalId);
        }
    }

    // Start the timer on page load
    intervalId = setInterval(updateTimer, 1000);

    function checkBoardCompletion() {
        for (let r = 0; r < 9; r++) {
            for (let c = 0; c < 9; c++) {
                if (board[r][c] !== solution[r][c]) {
                    return false;
                }
            }
        }
        return true;
    }

    // Checks board completion every second
    setInterval(() => {
        if (checkBoardCompletion) {
            let event = new Event('boardCompleted');
            document.dispatchEvent(event);
        }
    }, 1000);

    document.addEventListener('boardCompleted', () => {
        boardCompleted = true;
    });
});

