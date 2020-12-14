import React from "react";

const LaunchDetails = ({launch, loaded}) => {

  if (!loaded){
    return <p>Loading...</p>
  }

  return (
    <>
      <h3>{launch.mission_name}</h3>
      <p>Flight Number: {launch.flight_number}</p>
      <p>Year: {launch.launch_year}</p>
      <p>Rocket: {launch.rocket.rocket_name}</p>
      <p>{launch.details}</p>
    </>
  )

}

export default LaunchDetails;