import React, { useState } from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";

import NavBar from "./components/NavBar";
import About from "./components/About";
import Home from "./components/Home";
import Pricing from "./components/Pricing";
import ErrorPage from "./components/ErrorPage";
import Choice from "./components/Choice";

const App = () => {
  let initialPricing = [
    { level: "Hobby", cost: 0 },
    { level: "Startup", cost: 10 },
    { level: "Enterprise", cost: 100 }
  ];
  
  const [pricing, setPricing] = useState(initialPricing);


  return (
    <Router>
      <>
        <NavBar />
        <Switch>
        <Route exact path="/" component={Home} />
        <Route path="/about" component={About} />
        <Route
          path="/pricing" exact
          render={() => <Pricing prices={pricing} />}
        />
        <Route path="/choices/:slug" component={Choice} />
        <Route component={ErrorPage}/>
        </Switch>
      </>
    </Router>
  );
}



export default App;