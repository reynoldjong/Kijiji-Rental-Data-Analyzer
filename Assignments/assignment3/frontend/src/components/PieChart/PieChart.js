import React, { useState, useEffect } from "react";
import Chart from "react-google-charts";

const PieChart = props => {
  const [data, setData] = useState({
    data: props.buildDataHandler("Furnished"),
    title:"furnished",
  });

 

  const updateData = event => {
    event.preventDefault();
    const plotPoints = props.buildDataHandler(event.target.value);
    setData({ title:event.target.value, data:plotPoints});
   
  };
  
  return (

      <div className="col">
        <div className="card shadow p-3 mb-5 bg-white rounded">
          <h3 style={{ textAlign: "left" }}>{data.title}</h3>
          <div className="card-body">
            <select onChange={updateData}>
              <option value="SmokingIncluded">Smoking Included</option>
              <option value="Bedrooms">Bedrooms</option>
              <option value="Bathrooms">Bathrooms</option>
              <option value="TV">TV</option>
              <option value="Elevator">Elevator</option>
              <option value="Furnished">Furnished</option>
              <option value="SmokingPermitted">Smoking Permitted</option>
              <option value="HydroIncluded">Hydro Included</option>
              <option value="HeatIncluded">Heat Included</option>
              <option value="WaterIncluded">Water Included</option>
              <option value="Internet">Internet</option>
              <option value="Landline">Landline Included</option>
            </select>

            <Chart
              width="100%"
              height="100%"
              chartType="PieChart"
              loader={<div>Loading Chart</div>}
              data={data.data}
              options={{
                title: data.title
              }}
            />
          </div>
        </div>
      </div>

  );
};

export default PieChart;
