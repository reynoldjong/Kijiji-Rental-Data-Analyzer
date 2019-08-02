import React,{useEffect,useState} from 'react';
import Chart from "react-google-charts";

/**
 * Since price distribution is very important this componenet is only used to render that data.
 * @param {*} props 
 */
const MainScatterPlot= (props) =>{
    return(
      <React.Fragment>
        <Chart
      width='100%'
      height='100%'
      chartType="Scatter"
      loader={<div>Loading Chart</div>}
      data={props.plotPoints}
      options={{
        title: props.title,
        chartArea: { width: "100%" },
        hAxis: {
          title: props.hTitle,
          minValue: 0
        },
        vAxis: {
          title: props.vTitle,
        }
      }}
      legendToggle
    />
    </React.Fragment>
    );
}

export default MainScatterPlot