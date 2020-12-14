import React from 'react';
import Pirate from "./Pirate";
import {Link} from 'react-router-dom';

const PirateDetail = ({pirate, onDelete}) => {


  const handleDelete = () => {
    onDelete(pirate.id)
  }

    if (!pirate){
      return "Loading..."
    }

    const piratesRaids = pirate.raids.map((raid, index) => {
      return <li key={index}>
      {raid.location}
      </li>
    })


    return (
      <div className = "component">
      <Pirate pirate = {pirate}/>
      <p>Raids:</p>
      <ul>
      {piratesRaids}
      </ul>
      <button onClick={handleDelete}>Delete {pirate.firstName}</button>
      </div>
    )
  }

export default PirateDetail;
