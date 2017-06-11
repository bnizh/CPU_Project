import React from 'react';
import { Button, Icon, Form, Input, Progress } from 'semantic-ui-react';
import $ from 'jquery';
import Calendar from '../utils/calendar/index'
import md5 from 'js-md5'

class RegistrationForm extends React.Component {

    constructor(props){
        super(props);
        this.state = {passScore: 10};
    }

    updatePassScore(){
        let score = 0;
        let pass = $('#passInput').val();
        if (!pass)
            return score;

        let letters = {};
        for (let i=0; i<pass.length; i++) {
            letters[pass[i]] = (letters[pass[i]] || 0) + 1;
            score += 5.0 / letters[pass[i]];
        }

        let variations = {
            digits: /\d/.test(pass),
            lower: /[a-z]/.test(pass),
            upper: /[A-Z]/.test(pass),
            nonWords: /\W/.test(pass),
        };

        let variationCount = 0;
        for (let check in variations) {
            variationCount += (variations[check] === true) ? 1 : 0;
        }
        score += (variationCount - 1) * 10;
        score = (score/60)*100;
        this.setState({ passScore: score });
    }

    handleLoginKeyUp = (componentId) => {
        if (componentId === 'idInput') {
            let input = $('#' + componentId);
            if (input.val().length > 11) {
                input.val(input.val().substring(0, 11));
            }
        } else if (componentId === 'passInput') {
            this.updatePassScore();
        }
     };

    render() {
        return (
            <div>
                <span>{this.props.header}</span>
                <Form onSubmit={this.postRequest}>
                    <Form.Field>
                        <Input type='number' id="idInput" onChange={() => this.handleLoginKeyUp('idInput')}
                            label={{ icon: 'asterisk' }}
                            labelPosition='left corner'
                            placeholder='პირადი ნომერი'
                        />
                    </Form.Field>
                    <Form.Field>
                        <Input  type={'password'} id="passInput" onChange={() => this.handleLoginKeyUp('passInput')}
                            label={{ icon: 'asterisk' }}
                            labelPosition='left corner'
                            placeholder='პაროლი'
                        />
                        <Progress percent={this.state.passScore} indicating/>
                    </Form.Field>
                    <Form.Field>
                        <Input id="nameInput"
                            label={{ icon: 'asterisk' }}
                            labelPosition='left corner'
                            placeholder='სახელი'
                        />
                    </Form.Field>
                    <Form.Field>
                        <Input id='mailInput' type={'mail'} iconPosition='left' placeholder='Email'>
                            <Icon name='at' />
                            <input />
                        </Input>
                    </Form.Field>
                    <Form.Field>
                        <Input type='number' id="mobileInput"
                               label={{ icon: 'asterisk' }}
                               labelPosition='left corner'
                               placeholder='ტელეფონის ნომერი'
                        />
                    </Form.Field>
                    <Form.Field>
                        <Calendar format={"DD-MM-YYYY"} closeOnSelect={true} todayText={'დღეს'} inputFieldClass={"dateValue"} placeholder={'აირჩიეთ დაბადების თარიღი'} nonEditable={true} inputFieldId={"dateInput"} />
                    </Form.Field>
                    <Button type='submit'>დადასტურება</Button>
                </Form>
            </div>
        );
    }

    postRequest = () => {
        let pass = md5($('#passInput').val());
        let data = {
            id : $('#idInput').val(),
            pass : pass,
            email : $('#mailInput').val(),
            name : $('#nameInput').val(),
            date : $('#dateInput').val(),
            mobile : $('#mobileInput').val()
        };
        console.log(data);
        $.ajax({
            url: "http://localhost:8080/signUp",
            method: "POST",
            data: data,
            dataType: "html"
        });
    }
}

export default RegistrationForm;