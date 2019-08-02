import React, { useState, useEffect } from "react";
import Chart from "react-google-charts";

const PieChart = props => {
  useEffect(() => {
   
    const plotPoints = props.buildDataHandler("Furnished");
    setData(plotPoints);
    setTitle("Furnished");
  
  },[]);
  const [data, setData] = useState({
    data:props.buildDataHandler("Furnished"),
  });
  const [title, setTitle] = useState({
    title:"Furnished",
  });


  const updateData =(event) =>{
    event.preventDefault();
    const plotPoints = props.buildDataHandler(event.target.value);
    setData(plotPoints);
    setTitle(event.target.value);
  }

  return (
    <React.Fragment>
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
        <option value="WaterIncluded">WaterIncluded</option>
        <option value="Internet">Internet</option>
        <option value="Landline">Landline Included</option>
      </select>

      <Chart
        width='100%'
        height='100%'
        chartType="PieChart"
        loader={<div>Loading Chart</div>}
        data={data}
        options={{
          title: title,
        
        }}
      />
    </React.Fragment>
  );
};

export default PieChart;
