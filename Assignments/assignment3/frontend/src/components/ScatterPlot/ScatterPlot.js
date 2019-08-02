import React, { useEffect, useState } from "react";
import Chart from "react-google-charts";

const MainScatterPlot = props => {
  const [data, setData] = useState({
    data: props.buildDataHandler("Bathrooms")
  });

  const [title, setTitle] = useState({
    title: "Furnished"
  });

  const updateData = event => {
    event.preventDefault();
    const plotPoints = props.buildDataHandler(event.target.value);
    setData(plotPoints);
    setTitle(event.target.value);
  };

  return (
    <React.Fragment>
        <div className="col">
                  <div className="card shadow p-3 mb-5 bg-white rounded">
                    <h3 style={{ textAlign: "left" }}>Scatter Plot</h3>
                    <div className="card-body">
      <select onChange={updateData}>
        <option value="Bedrooms">Bedrooms</option>
        <option value="Bathrooms">Bathrooms</option>
      
      </select>

      <Chart
        width="100%"
        height="100%"
        chartType="Scatter"
        loader={<div>Loading Chart</div>}
        data={data}
        options={{
          title: title,
          chartArea: { width: "100%" },
          hAxis: {
            title: props.hTitle,
            minValue: 0
          },
          vAxis: {
            title: props.vTitle
          }
        }}
        legendToggle
      />
         </div>
                  </div>
                </div>
    </React.Fragment>
  );
};

export default MainScatterPlot;
