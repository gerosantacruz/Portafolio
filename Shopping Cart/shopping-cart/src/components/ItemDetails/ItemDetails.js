import React, {Component} from 'react';
import {Button, Collapse, Well, Media, Row, Col} from 'react-bootstrap';

export default class ItemDetails extends Component{
    constructor(props){
        super(props);
        this.state = {
            open: false
        };
    }

    render(){
        return(
            <div>
                <Button
                className='item-details-button'
                bsStyle='link'
                onClick={() => this.setState({open: !this.state.open})}>
                    {this.state.open === false ? `See`: `Hide `} item details
                    {this.state.open === false ? ` +` : ` -`}
                </Button>

                <Collapse in={this.state.open}>
                    <div>
                        <Well>
                            <Media>
                                <Media.Left>
                                    <img width={100} height={100}  
                                    src="https://1.bp.blogspot.com/-n9F0HDrSVB0/V3fAzf8_-3I/AAAAAAAAAf0/247G3ZAAT4sI56-EdUDpjejN_uo2T7IDQCLcB/s1600/Samsung-Galaxy-S9-Edge-Release-date-Price-Specification.jpg" alt="Samsung s9"/>
                                </Media.Left>
                                <Media.Body>
                                    <p>New Samsung 9s with 64GB Dual-chip</p>
                                    <Row className="show-grid">
                                        <Col md={6}>
                                            <strong> {`$${this.props.price}`}</strong>
                                            <br />
                                            <strong className="price-strike">{`$${this.props.price}`}</strong>
                                        </Col>
                                        <Col md={6}> Quantity: 1</Col>
                                    </Row>
                                </Media.Body>
                            </Media>
                        </Well>
                    </div>
                </Collapse>
            </div>
        );
    }
}