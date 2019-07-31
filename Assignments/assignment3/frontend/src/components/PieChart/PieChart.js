import React from "react";
import Chart from "react-google-charts";

const PieChart = props => {
  return (
    <Chart
      width={300}
      height={300}
      chartType="PieChart"
      loader={<div>Loading Chart</div>}
      data={[["Age", "Weight"], ["a", 12], ["b", 5.5]]}
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
};

export default PieChart;
