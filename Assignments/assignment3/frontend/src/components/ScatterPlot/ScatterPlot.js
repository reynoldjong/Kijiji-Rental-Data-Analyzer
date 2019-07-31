import React from 'react';
import Chart from "react-google-charts";

const ScatterPlot= (props) =>{
    return(
        <Chart
      width={300}
      height={300}
      chartType="Scatter"
      loader={<div>Loading Chart</div>}
      data={[
        ['Hours Studied', 'Final'],
        [0, 67],
        [1, 88],
        [2, 77],
        [3, 93],
        [4, 85],
        [5, 91],
        [6, 71],
        [7, 78],
        [8, 93],
        [9, 80],
        [10, 82],
        [0, 75],
        [5, 80],
        [3, 90],
        [1, 72],
        [5, 75],
        [6, 68],
        [7, 98],
        [3, 82],
        [9, 94],
        [2, 79],
        [2, 95],
        [2, 86],
        [3, 67],
        [4, 60],
        [2, 80],
        [6, 92],
        [2, 81],
        [8, 79],
        [9, 83],
        [3, 75],
        [1, 80],
        [3, 71],
      ]}
      options={{
        title: props.title,
        chartArea: { width: "30%" },
        hAxis: {
          title: props.hTitle,
          minValue: 0
        },
        vAxis: {
          title: props.vTtitle,
        }
      }}
      legendToggle
    />
    );
}

export default ScatterPlot