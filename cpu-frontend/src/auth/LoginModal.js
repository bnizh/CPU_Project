import  React from "react";
import { Button, Form, Input } from 'semantic-ui-react';
import $ from 'jquery';

class LoginModal extends React.Component {

    handleLoginKeyUp = (componentId) => {
        if (componentId === 'idInput') {
            let input = $('#' + componentId);
            if (input.val().length > 11) {
                input.val(input.val().substring(0, 11));
            } else if (!$.isNumeric(input.val())) {
                input.val(input.val().substring(0, input.val().length - 1));
            }
        }
    };

    render() {
        return (
            <div>
                <span>{this.props.header}</span>
                <Form onSubmit={this.postRequest}>
                    <Form.Field>
                        <Input id="idInput" onChange={() => this.handleLoginKeyUp('idInput')}
                               labelPosition='left corner'
                               placeholder='პირადი ნომერი'
                        />
                    </Form.Field>
                    <Form.Field>
                        <Input type={'password'} id="passInput"
                            labelPosition='left corner'
                            placeholder='პაროლი'
                        />
                    </Form.Field>
                    <Button type='submit'>სისტემაში შესვლა</Button>
                </Form>
            </div>
        );
    }

    postRequest = (event) => {
        $.ajax({
            url: "http://localhost:8080/logIn",
            method: "POST",
            data: {
                id : $('#idInput').val(),
                pass : $('#passInput').val()
            },
            dataType: "html"
        });
    }
}

export default LoginModal;