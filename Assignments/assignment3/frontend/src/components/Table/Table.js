import React from 'react';
import classes from './Table.module.css';

const table = (props) =>{
    return(
        <React.Fragment>

     <div className={classes.ScrollableTable}>


        <table class="table" >
  <thead className={classes.TableColor}>
    <tr>
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
    <tr>
      <th scope="row">1</th>
      <td>Mark</td>
      <td>Otto</td>
      <td>@mdo</td>
    </tr>
    <tr>
      <th scope="row">2</th>
      <td>Jacob</td>
      <td>Thornton</td>
      <td>@fat</td>
    </tr>
    <tr>
      <th scope="row">3</th>
      <td>Larry</td>
      <td>the Bird</td>
      <td>@twitter</td>
    </tr>
  </tbody>
</table>
</div>
</React.Fragment>
    )
};

export default table;