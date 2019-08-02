import React, { Component } from "react";
import { Map, GoogleApiWrapper, Marker } from "google-maps-react";
import PieChart from "./components/PieChart/PieChart";
import MainScatterPlot from "./components/MainScatterPlot/MainScatterPlot";
import Navbar from "./components/Navbar/Navbar";
import axios from "axios";
import Table from "./components/Table/Table";
import ScatterPlot from "./components/ScatterPlot/ScatterPlot";
import RentalInfoWindow from "./components/Window/Window";

const mapStyles = {
  width: "100%",
  height: "100%"
};

export class MapContainer extends Component {
  /**
   * State holds variables on every crawlled element.
   * These varaibles get passed onto the charts.  The benefit to this approach
   * is that only one get request needs to be made for the chartview and one
   * more for the graphs view. Data needs to be in list format for easier
   * maping of components
   *
   */
  constructor(props) {
    super(props);

    this.state = {
      details: [],
      data: {},
      Prices: [],
      Addresses: [],
      Bedrooms: [],
      Bathrooms: [],
      TV: [],
      Elevator: [],
      Furnished: [],
      SmokingPermitted: [],
      HydroIncluded: [],
      HeatIncluded: [],
      WaterIncluded: [],
      UnitType: [],
      Internet: [],
      Landline: [],
      Yard: [],
      Size: [],
      rows: []
    };
  }
<<<<<<< HEAD

  /**
   * When the component is created we should create retrieve the data
   * for the map section and charts section
   */
  componentDidMount() {
    axios
      .get("/mapview")
      .then(response => {
        // If the get request is successful state (files) is updated
        this.updateDetails(response);
      })
      .catch(function(error) {
        console.log(error);
      });

    this.getData();
  }

  updateDetails = response => {
    const data = response["data"]["rental"];
    this.setState({
      ...this.state,
      details: data
    });
  };

  /**
   * Display the markers on the map correspond to the locations of the listing
   */
  displayMarkers = () => {
    return this.state.details.map((details, index) => {
      return (
        <Marker
          key={index}
          id={index}
          place={details}
          onClick={this.onMarkerClick}
          position={{
            lat: details.lat,
            lng: details.lng
          }}/>
      );
    });
  };

  /**
   * Pop up an info window when the marker is clicked
   */
  onMarkerClick = (props, marker) => {
    this.setState({
      activeMarker: marker,
      selectedAddress: props.place.address,
      selectedPrice: props.place.price,
      selectedUrl: props.place.url,
      selectedId: props,
      showingInfoWindow: true
    });
  };

  /**
   * Close the info window box when the cross button is clicked
   */
  onInfoWindowClose = () =>
    this.setState({
      activeMarker: null,
      showingInfoWindow: false
    });

  /**
   * Close the info window box when the map is clicked while info window box is on
   */
  onMapClicked = () => {
    if (this.state.showingInfoWindow)
      this.setState({
        activeMarker: null,
        showingInfoWindow: false
      });
  };

  handleClick = () => {
    this.setState({ open: true });
  };

  handleClose = (event, reason) => {
    if (reason === 'clickaway') {
      return;
    }
    this.setState({ open: false });
  };

  /**
   * Makes a get request to /chartview which adds crawler data to state
   */
  getData = async () => {
    await axios
      .get("/chartview")
      .then(response => {
        // this.setData(data);
        this.getRows(response);
        this.setFields(response);
      })
      .catch(error => {
        console.log(error);
      });
  };

  /**
   * Helper function for setting state
   */
  setData = data => {
    this.setState({
      ...this.state,
      data: data
    });
  };

  /**
   * Maps all the raw data to a rows state variable
   */
  getRows = response => {
    let rows = [];
    const data = response["data"];

    for (var key in data) {
      let row = null;

      row = data[key];

      rows.push(row);
    }
    this.setState({ ...this.state, rows: rows });
  };

  /**
   * Maps all the fields in the crawler data to their appropriate state
   * counterpart.
   */
  setFields = response => {
    const data = response["data"];
    // Set up lists to hold data
    let Prices = [];
    let Bedrooms = [];
    let Bathrooms = [];
    let Addresses = [];
    let TV = [];
    let Elevator = [];
    let Furnished = [];
    let SmokingPermitted = [];
    let HydroIncluded = [];
    let HeatIncluded = [];
    let WaterIncluded = [];
    let UnitType = [];
    let Internet = [];
    let Landline = [];
    let Yard = [];
    let Size = [];
    // Loop through response maping variables
    for (var listing in data) {
      Prices.push(data[listing]["Price"]);
      Bedrooms.push(data[listing]["Bedrooms"]);
      Bathrooms.push(data[listing]["Bathrooms"]);
      Addresses.push(data[listing]["Address"]);
      TV.push(data[listing]["Cable/TV Included"]);
      Elevator.push(data[listing]["Elevator in Building"]);
      Furnished.push(data[listing]["Furnished"]);
      SmokingPermitted.push(data[listing]["Smoking Permitted"]);
      HydroIncluded.push(data[listing]["Hydro Included"]);
      HeatIncluded.push(data[listing]["Heat Included"]);
      WaterIncluded.push(data[listing]["Water Included"]);
      UnitType.push(data[listing]["Unit Type"]);
      Internet.push(data[listing]["Internet Included"]);
      Landline.push(data[listing]["Landline Included"]);
      Yard.push(data[listing]["Yard Balcony"]);
      Size.push(data[listing]["Elevator in Building"]);
    }
    // update state
    this.setState({
      ...this.state,
      Prices: Prices,
      Bedrooms: Bedrooms,
      Bathrooms: Bathrooms,
      Addresses: Addresses,
      TV: TV,
      Elevator: Elevator,
      Furnished: Furnished,
      SmokingPermitted: SmokingPermitted,
      HydroIncluded: HydroIncluded,
      HeatIncluded: HeatIncluded,
      WaterIncluded: WaterIncluded,
      UnitType: UnitType,
      Internet: Internet,
      Landline: Landline,
      Yard: Yard,
      Size: Size
    });
  };

  /**
   * Creates a plotPoints array for the prices category.  Prices
   * however have "," and "$" for each home and need to be removed and
   * then converted to float.  If the price is null it will be made
   * into a 0.
   */
  getPricesPlotPoints = () => {
    const prices = this.state.Prices;
    let plotPoints = [];

    plotPoints.push(["Home", "Price"]);
    prices.forEach(function(price, homeNumber) {
      let alteredPrice = price;
      alteredPrice = alteredPrice.replace(",", "");
      alteredPrice = alteredPrice.replace("$", "");
      if (alteredPrice === "") {
        alteredPrice = 0;
      }
      alteredPrice = parseFloat(alteredPrice);
      plotPoints.push([homeNumber, alteredPrice]);
    });
    return plotPoints;
  };

  /**
   * Given a valid type name (the ones used in state), this function will
   * return the appropriate array of each point to be used in a scatter plot.
   * All the values need to be converted to float.
   * @param type: a variable name describe in state
   */
  buildScatterPlotData = type => {
    let plotPoints = [];

    plotPoints.push(["Home", type]);

    this.state[type].forEach(function(item, index) {
      plotPoints.push([index, parseFloat(item)]);
    });
    return plotPoints;
  };

  /**
   * Given a valid type name (the ones used in state), this function
   * return the appropriate array of each point to be used in a pie chart.
   * @param type: a variable name describe in state
   */
  buildPieChartData = type => {
    let plotPoints = [];
    let dict = {};
    // Dummy header
    plotPoints.push(["X", type]);
    // Loop through each key and add 1 more if the key exists in dict
    // Otherwise make an entry for it
    this.state[type].forEach(function(item, index) {
      if (dict.hasOwnProperty(item)) {
        dict[item] += 1;
      } else {
        dict[item] = 1;
      }
    });
    for (var key in dict) {
      plotPoints.push([key, dict[key]]);
    }
    return plotPoints;
  };

  render() {
    return (
      <React.Fragment>
        <div style={{ position: "relative", left: "auto", right: "auto" }}>
          <Navbar />
          <div className="container-fluid" style={{ marginTop: "40px" }}>
            <center>
              <div className="row">
                <div className="col">
                  <div className="card shadow p-3 mb-5 bg-white rounded">
                    <h3 style={{ textAlign: "left" }}>Price Distribution</h3>
                    <div className="card-body ">
                      <MainScatterPlot
                        title="Price Distribution Across Homes"
                        vTitle="Price in $CAD"
                        hTitle="Home"
                        plotPoints={this.getPricesPlotPoints()}
                      />
                    </div>
                  </div>
                </div>
              </div>
            </center>
            <center>
              <div className="row">
                <PieChart
                  vTitle="vTitle"
                  hTitle="hTitle"
                  buildDataHandler={this.buildPieChartData}
                  initialPoints={this.buildPieChartData("Furnished")}
                />

                <PieChart
                  vTitle="vTitle"
                  hTitle="hTitle"
                  buildDataHandler={this.buildPieChartData}
                  initialPoints={this.buildPieChartData("Furnished")}
                />
              </div>
              <div className="row">
                <ScatterPlot
                  title="Chart"
                  vTitle="vTitle"
                  hTitle="hTitle"
                  buildDataHandler={this.buildScatterPlotData}
                  initialPoints={this.buildScatterPlotData("Bedrooms")}
                />

                <ScatterPlot
                  title="Chart"
                  vTitle="vTitle"
                  hTitle="hTitle"
                  buildDataHandler={this.buildScatterPlotData}
                  initialPoints={this.buildScatterPlotData("Bedrooms")}
                />
              </div>
            </center>

            <center>
              <div className="row">
                <div className="col">
                  <div className="card shadow p-3 mb-5 bg-white rounded">
                    <h3 style={{ textAlign: "left" }}>View all Data</h3>
                    <div className="card-body ">
                      <Table rows={this.state.rows} />
                    </div>
                  </div>
                </div>
              </div>
            </center>
          </div>
          <Map
            google={this.props.google}
            onClick={this.onMapClicked}
            zoom={8}
            style={mapStyles}
            initialCenter={{ lat: 43.7645, lng: -79.411 }}
          >
            {this.displayMarkers()}
           <RentalInfoWindow
           marker={this.state.activeMarker}
           visible={this.state.showingInfoWindow}
           >
           <div>
           <h1>{this.state.selectedAddress}</h1>
           <p>{this.state.selectedPrice}</p>
           <span>{this.state.selectedUrl}</span>
         </div>
         </RentalInfoWindow>
          </Map>
        </div>
      </React.Fragment>
    );
  }
}

export default GoogleApiWrapper({
  apiKey: "AIzaSyDAcLK0RSSSVNdvAA_TRGAHPPHpBZnIEiw"
})(MapContainer);
