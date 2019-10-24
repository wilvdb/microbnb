import React from "react";
import { Modal, Form, Input, Radio, Icon } from 'antd';

class CreateAccountForm extends React.Component {

    render() {
        const { visible, onCancel, onCreate, form } = this.props;
      const { getFieldDecorator } = form;
      return (
        <Modal
          visible={visible}
          //title="Create a new collection"
          okText="Create"
          onCancel={onCancel}
          onOk={onCreate}
        >
          <Form layout="vertical">
            <Form.Item>
              {getFieldDecorator('email', {
                rules: [{ required: true, }],
              })(<Input suffix={<Icon type="mail" style={{ color: 'rgba(0,0,0,.45)' }}/>} placeholder="Mail address" size="large" />)}
            </Form.Item>
            <Form.Item>
              {getFieldDecorator('firstname', {
                rules: [{ required: true,  }],
              })(<Input suffix={<Icon type="user" style={{ color: 'rgba(0,0,0,.45)' }}/>} type="textarea" placeholder="First name" size="large" />)}
            </Form.Item>
            <Form.Item>
              {getFieldDecorator('lastname', {
                rules: [{ required: true, }],
              })(<Input suffix={<Icon type="user" style={{ color: 'rgba(0,0,0,.45)' }}/>} type="textarea" placeholder="Last name" size="large" />)}
            </Form.Item>
            <Form.Item>
              {getFieldDecorator('password', {
                rules: [{ required: true, }],
              })(<Input.Password type="textarea" placeholder="Generate password" size="large" />)}
            </Form.Item>
          </Form>
        </Modal>
      );
    }
  }

export default Form.create({ name: 'form_in_modal' })( CreateAccountForm );
