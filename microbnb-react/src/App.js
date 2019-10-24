import React from 'react';
import './App.css';

import CreateAccountForm from './account/CreateAccountForm';
import { Button } from 'antd';

export class App extends React.Component {

state = {
  visible: false,
};

showModal = () => {
  this.setState({ visible: true });
};

handleCancel = () => {
  this.setState({ visible: false });
};

handleCreate = () => {
  const { form } = this.formRef.props;
  form.validateFields((err, values) => {
    if (err) {
      return;
    }

    console.log('Received values of form: ', values);
    form.resetFields();
    this.setState({ visible: false });
  });
};

saveFormRef = formRef => {
  this.formRef = formRef;
};

render() {
  return (
    <div>
      <Button type="primary" onClick={this.showModal}>
        New Collection
      </Button>
      <CreateAccountForm
        wrappedComponentRef={this.saveFormRef}
        visible={this.state.visible}
        onCancel={this.handleCancel}
        onCreate={this.handleCreate}
      />
    </div>
  );
}
}
