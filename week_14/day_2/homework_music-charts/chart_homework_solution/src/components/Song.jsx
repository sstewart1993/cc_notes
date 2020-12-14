import React from 'react';

function Song(props) {

  const altTag = `${props.title} by ${props.artist}`;
  let audioSrc;

  return (
    <div className="song">
      <img
        alt={"Play " + altTag}
        id={props.position}
        className='audio-control'
        src='https://image.freepik.com/free-icon/play-button_318-42541.jpg'
        onClick={() => { props.handlePlayPause(audioSrc)}}
      />

      <img
        src={props.image}
        alt={altTag} />

      <div className='details'>
        <h3>{props.position}. {props.title}</h3>
        <h4>{props.artist}</h4>
      </div>

      <audio
        ref={(audio) => audioSrc = audio}
        id={'audio' + props.position}
        src={props.audio} />
    </div>
  );
};

export default Song;
