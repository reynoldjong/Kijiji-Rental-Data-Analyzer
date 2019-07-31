import React from 'react';
import Chart from "react-google-charts";

const BarChart = (props) =>{
    return(
        <Chart
      width={300}
      height={300}
      chartType="ColumnChart"
      loader={<div>Loading Chart</div>}
      data={[
        ['City', '2010 Population', '2000 Population'],
        ['New York City, NY', 8175000, 8008000],
        ['Los Angeles, CA', 3792000, 3694000],
        ['Chicago, IL', 2695000, 2896000],
        ['Houston, TX', 2099000, 1953000],
        ['Philadelphia, PA', 1526000, 1517000],
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

export default BarChart