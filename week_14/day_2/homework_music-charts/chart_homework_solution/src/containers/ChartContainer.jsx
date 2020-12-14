import React, { useEffect, useState } from 'react';
import Chart from '../components/Chart';
import TitleBar from '../components/TitleBar';

const ChartContainer = ({ genres }) => {
  const [songs, setSongs] = useState([]);

  useEffect(() => {
   loadSongs(genres[0].url)
  }, [genres])

  const loadSongs = url => {
    fetch(url)
      .then(res => res.json())
      .then(songsList => setSongs(songsList.feed.entry))
      .catch(err => console.error);
  }

  const handlePlayPause = audio => {
    audio.paused ? audio.play() : audio.pause();
    audio.classList.toggle('playing');
  }

  const handleSelectChange = event => {
    loadSongs(event.target.value);
  }

  return (
    <>
      <TitleBar
        handleSelectChange={handleSelectChange}
        genres={genres}
      />
      <Chart
        songs={songs}
        url={genres[0].url}
        handleSelectChange={handleSelectChange}
        handlePlayPause={handlePlayPause}
      />
    </>
  )
}

export default ChartContainer;
