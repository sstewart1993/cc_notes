import React, {useState, useEffect} from "react";
import LaunchSelector from "../components/LaunchSelector";
import LaunchDetails from "../components/LaunchDetails";

const LaunchContainer = () => {

  const [launch, setLaunch] = useState({});
  const [selectedLaunchId, setSelectedLaunchId] = useState(1);
  const [loaded, setLoaded] = useState(false);

  useEffect(() => {
    getLaunch();
  }, [selectedLaunchId]);

  const getLaunch = () => {
    console.log("getting launch data");
    
    fetch(`https://api.spacexdata.com/v3/launches/${selectedLaunchId}`)
      .then(res => res.json())
      .then(data => setLaunch(data))
      .then(() => setLoaded(true))
  }

  const incrementSelectedLaunch = () => {
    const nextLaunchIndex = selectedLaunchId + 1;

    if (nextLaunchIndex <= 90){
      setSelectedLaunchId(nextLaunchIndex);
    }
  }

  const decrementSelectedLaunch = () => {
    const nextLaunchIndex = selectedLaunchId - 1;

    if (nextLaunchIndex >= 1){
      setSelectedLaunchId(nextLaunchIndex);
    }
  }

  return (
    <>
      <h1>SpaceX Launch Details</h1>
      <LaunchSelector 
        onSelectedLaunchIncrement={() => incrementSelectedLaunch()}
        onSelectedLaunchDecrement={() => decrementSelectedLaunch()}
      />
      <LaunchDetails 
        launch={launch}
        loaded={loaded}
      />
    </>
  )

}

export default LaunchContainer;