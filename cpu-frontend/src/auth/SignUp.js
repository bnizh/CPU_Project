import React from 'react';
import CustomModal from '../modal/CustomModal';
import { Button } from 'semantic-ui-react';
import RegistrationForm from "./RegistrationForm";


class SignUp extends React.Component {
    constructor(props) {
        super(props)
        this.state = { dimmer:'blurring', open: false  }
    }

    render() {
        return (
            <div>
                <Button style={{float: 'right', margin:'10px'}} onClick={() => this.openModal()}>Sign up</Button>
                <CustomModal customForm={<RegistrationForm />} header='შეიყვანეთ ინფორმაცია' dimmer={this.state.dimmer} isOpen={this.state.open} onClose={() => this.closeModal()}>
                </CustomModal>
            </div>
        )
    }

    openModal() {
        this.setState({ dimmer: 'blurring', open: true })
    }

    closeModal() {
        this.setState({ dimmer: true , open: false })
    }
}

export default SignUp;