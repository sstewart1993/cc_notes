import React, {useState, Fragment} from "react";

const PiggyBank = ({title, depositAmount}) => {

  const [total, setTotal] = useState(0);

  const deposit = () => {
    setTotal(total + depositAmount);
  }

  const withdraw = () => {
    let newAmount = total - depositAmount;

    if(newAmount < 0){
      newAmount = 0;
    }

    setTotal(newAmount);
  }

  return(
    <>
      <h1>{title}</h1>
      <p>Total: £{total}</p>
      <button onClick={() => deposit()}>Deposit</button>
      <button onClick={() => withdraw()}>Withdraw</button>
    </>
  )
}

export default PiggyBank;