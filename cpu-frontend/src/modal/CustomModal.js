import * as React from "react";
import { Modal } from 'semantic-ui-react';

class CustomModal extends React.Component {

    render() {
        return (
            <div>
                <Modal dimmer={this.props.dimmer} open={this.props.isOpen} onClose={this.props.onClose}>
                    <Modal.Header>{this.props.header}</Modal.Header>
                    <Modal.Content>
                        {this.props.customForm}
                    </Modal.Content>
                </Modal>
            </div>
        )
    }
}

export default CustomModal;