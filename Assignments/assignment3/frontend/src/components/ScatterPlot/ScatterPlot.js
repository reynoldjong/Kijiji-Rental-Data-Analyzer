import React,{useEffect,useState} from 'react';
import Chart from "react-google-charts";

const ScatterPlot= (props) =>{
  const options2={
    title: props.title,
    chartArea: { width: "100%" },
    hAxis: {
      title: props.hTitle,
      minValue: 0
    },
    vAxis: {
      title: props.vTitle,
    }
  }
    return(
      <React.Fragment>
      <select onChange={updateData}>
        <option value="Bedrooms">Bedrooms</option>
        <option value="Bathrooms">Bathrooms</option>
      </select>

        <Chart
      width='100%'
      height='100%'
      chartType="Scatter"
      loader={<div>Loading Chart</div>}
      data={props.plotPoints}
      options={options2}
      legendToggle
    />
    </React.Fragment>
    );
}

export default ScatterPlot