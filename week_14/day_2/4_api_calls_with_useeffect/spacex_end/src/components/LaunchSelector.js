import React from "react";

const LaunchSelector = ({onSelectedLaunchIncrement, onSelectedLaunchDecrement}) => {

  return (
    <>
      <button onClick={onSelectedLaunchDecrement}>Previous Launch</button>
      <button onClick={onSelectedLaunchIncrement}>Next Launch</button>
    </>
  )

}

export default LaunchSelector;