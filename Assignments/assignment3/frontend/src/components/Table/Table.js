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
<td>{item["Address"]}</td>
<td>{item["Unit Type"]}</td>
<td>{item["Price"]}</td>
<td>{item["Url"]}</td>
<td>{item["Bedrooms"]}</td>
<td>{item["Bathrooms"]}</td>
<td>{item["Parking Included"]}</td>
<td>{item["Move-In Date"]}</td>
<td>{item["Pet Friendly"]}</td>
<td>{item["Size (sqft)"]}</td>
<td>{item["Furnished"]}</td>
<td>{item["Smoking Permitted"]}</td>
<td>{item["Hydro Included"]}</td>
<td>{item["Heat Included"]}</td>
<td>{item["Water Included"]}</td>
<td>{item["Cable/TV Included"]}</td>
<td>{item["Internet Included"]}</td>
<td>{item["Landline Included"]}</td>
<td>{item["Yard Balcony"]}</td>
<td>{item["Elevator in Building"]}</td>
</tr>)})}
          </tbody>
        </table>
      </div>
    </React.Fragment>
  );
};

export default table;
