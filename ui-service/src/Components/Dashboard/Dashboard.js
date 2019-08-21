import React from "react";
import "../Components CSS/Dashboard/Dashboard.css";
import "bootstrap-4-grid/css/grid.min.css";
import { Container, Row, Col } from "reactstrap";

class Dashboard extends React.Component {
  render() {
    return (
      <Container>
        <Row>
          <Col>  Nav Bar/ Search Bar</Col>
        </Row>
        <Row>
          <Col>dsa</Col>
        </Row>
        <Row>
          <Col xs="3">.col-3</Col>
          <Col xs=""></Col>
          <Col xs="3">.col-3</Col>
        </Row>
        <Row>
          <Col xs="6">.col-6</Col>
          <Col xs="6">.col-6</Col>
        </Row>
        <Row>
          <Col xs="6" sm="4">.col-6 .col-sm-4</Col>
          <Col xs="6" sm="4">.col-6 .col-sm-4</Col>
          <Col sm="4">.col-sm-4</Col>
        </Row>
      </Container>
    );
  }
}

export default Dashboard;
