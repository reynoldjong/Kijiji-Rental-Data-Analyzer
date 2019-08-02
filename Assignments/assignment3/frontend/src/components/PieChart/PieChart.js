import React, { useState, useEffect } from "react";
import Chart from "react-google-charts";

/**
 * Component used to render a pie chart, data must be formated
 * as follows [["header1", "header2"], [data1, data2]....]
 * @props 
 */
const PieChart = props => {

  /**
   * State for PieChart, only contains a title used for the pie chart and 
   * the plot points for the pie chart
   */
  const [data, setData] = useState({
    data: props.initialPoints,
    title:"furnished",
  });

  /**
   * Once the component is being rendered we need to populate the graph
   * with something, initial points is used to pre-populate the data
   */
 useEffect(()=>{
  setData({title:"Furnished", data:props.initialPoints});
 },[props.initialPoints]);

  /**
   * Once a user selects a different type of data from the data
   * this function will change the data and title that is used in useState
   * @event 
   */
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
