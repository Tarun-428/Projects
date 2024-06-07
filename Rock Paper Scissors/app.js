let userScore = 0;
let compScore = 0;


const choices = document.querySelectorAll(".choice");
const msg = document.querySelector("#msg");
const userScorePara = document.querySelector("#user-score");
const compScorePara = document.querySelector("#comp-score");

const showWinner = (userWin,userChoice,compChoice) =>{
    if(userWin == true){
        userScore++;
        userScorePara.innerText = userScore;
        msg.innerText = `You Win ! Your ${userChoice} beats ${compChoice}`;
        msg.style.backgroundColor = "green";

    }else {
        compScore++;
        compScorePara.innerText = compScore;
        msg.innerText = `You Lose ! Computer ${compChoice} beats your ${userChoice}`;
        msg.style.backgroundColor = "red";
    }
}

const genCompChoice = () => {
    const options = ["rock","paper","scissors"];
    const randIdx = Math.floor(Math.random()*3);
    return options[randIdx];
 
}



const playGame = (userChoice) => {
        
        const compChoice = genCompChoice();

        if(userChoice == compChoice){
            console.log("Drawn");
            msg.innerText = "Game is Draw ! ";
            msg.style.backgroundColor = "#081b31";
        } else{
            let userWin = true;
            if(userChoice == "rock"){
               userWin =  compChoice === "paper" ? false : true;
            } else if(userChoice == "paper"){
                userWin = compChoice === "scissors" ? false : true;
            }  else {
                userWin = compChoice === "rock" ? false : true;
            }
            showWinner(userWin, userChoice, compChoice);
            
        } 
};


choices.forEach((choice)=>{
    choice.addEventListener("click",()=>{
        const userChoice = choice.getAttribute("id");
        
        playGame(userChoice);
    });
});
