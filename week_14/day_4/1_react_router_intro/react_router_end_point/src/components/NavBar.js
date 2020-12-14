import React from "react";
import { Link, useHistory } from "react-router-dom";


const NavBar = () => {
  const history = useHistory();

  const goBack = () => {
    history.goBack();
  }

  return (
    <ul>
      <li>
        <Link to="/">Home</Link>
      </li>
      <li>
        <Link to="/about">About</Link>
      </li>
      <li>
        <Link to="/pricing">Pricing</Link>
      </li>
      <li>
        <button onClick={goBack}>Back</button>
      </li>
    </ul>
  );
}

export default NavBar;
