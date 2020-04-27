import React from "react";
import classes from "./Table.module.css";

/**
 * Component represents a table with all the data that was crawled
 * @props 
 */
const table = props => {
  return (
    <React.Fragment>
      <div className={classes.ScrollableTable}>
        <table class="table">
          <thead className={classes.TableColor}>
            <tr>
            <th scope="col">col #</th>
              <th scope="col">Address</th>
              <th scope="col">Unit Type</th>
              <th scope="col">Price</th>
              <th scope="col">URL</th>
              <th scope="col">Bedrooms</th>
              <th scope="col">Bathrooms</th>
              <th scope="col">Parking Included</th>
              <th scope="col">Move-In Date</th>
              <th scope="col">Pet Friendly</th>
              <th scope="col">Size (sqft)</th>
              <th scope="col">Furnished</th>
              <th scope="col">Smoking Permitted</th>
              <th scope="col">Hydro Included</th>
              <th scope="col">Heat Included</th>
              <th scope="col">Water Included</th>
              <th scope="col">Cable/TV Included</th>
              <th scope="col">Internet Included</th>
              <th scope="col">Landline Included</th>
              <th scope="col">Yard Balcony</th>
              <th scope="col">Elevator in Building</th>
            </tr>
          </thead>
          <tbody>
          {props.rows.map(function(item,index){
           
return(<tr>
<th scope="row">{index}</th>
<td>{item["address"]}</td>
<td>{item["unitType"]}</td>
<td>{item["price"]}</td>
<td>{item["url"]}</td>
<td>{item["bedrooms"]}</td>
<td>{item["bathrooms"]}</td>
<td>{item["parkingIncluded"]}</td>
<td>{item["moveInDate"]}</td>
<td>{item["petFriendly"]}</td>
<td>{item["sizeSqft"]}</td>
<td>{item["furnished"]}</td>
<td>{item["smokingPermitted"]}</td>
<td>{item["hydroIncluded"]}</td>
<td>{item["heatIncluded"]}</td>
<td>{item["waterIncluded"]}</td>
<td>{item["cableTvIncluded"]}</td>
<td>{item["internetIncluded"]}</td>
<td>{item["landlineIncluded"]}</td>
<td>{item["yardIncluded"]}</td>
<td>{item["elevatorInBuildingIncluded"]}</td>
</tr>)})}
          </tbody>
        </table>
      </div>
    </React.Fragment>
  );
};

export default table;
