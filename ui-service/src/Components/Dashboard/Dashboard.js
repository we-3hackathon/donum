import React from "react";
import "../Components CSS/Dashboard/Dashboard.css";
import "bootstrap-4-grid/css/grid.min.css";

class Dashboard extends React.Component {
  render() {
    return (
      <div className="bootstrap-wrapper">
        <div className="app-container container">
          <div className="row">
            <div className="col-9">dsadsa</div>
            <div className="col-1"></div>
            <div className="col-1"></div>
            <div className="col-1"></div>
          </div>
          <div className="row">
            <div className="col-3">side bar ting here</div>
            <div className="col">
              <div className="row">
                <div className="col-md-5">dsa</div>
                <div className="col-md-4">dsa</div>
            </div>
            </div>
          </div>
      </div>
      </div>
    );
  }
}

export default Dashboard;
