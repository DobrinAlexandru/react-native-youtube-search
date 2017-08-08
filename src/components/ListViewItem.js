import React, { Component } from 'react';
import { Text, TouchableWithoutFeedback, View, Image, Linking} from 'react-native';
class ListViewItem extends Component {
  pressCard(){
    // alert("boom");
    this.props.method();
  }

  renderRowText(){
    if(this.props.item != null && this.props.item.title != null) {
      return <View >
          <Text style={{ fontSize: 12}}>{this.props.item.title}</Text>
          <Text>{this.props.item.time} </Text>
        </View>;
    }
  }
  render() {
    return (
      <TouchableWithoutFeedback
        //  onPress={() => Linking.openURL("https://www.instagram.com/_u/" + album.user.username)}
        style={{  height: 120, width: 340, marginLeft: 20, marginRight:20 }}
         onPress={() => this.pressCard()}
        >
        <View style={{  height: 120, width: 340, flexDirection: 'row', alignItems: 'center', flex: 1 }}>
          <Image
              style={styles.thumbnailStyle}
              source={{ uri: this.props.item.image }}/>
          {this.renderRowText()}
        </View>
      </TouchableWithoutFeedback>
    );
  }
}
const styles = {
  headerContentStyle: {
    flexDirection: 'column',
    justifyContent: 'space-around'
  },
  headerTextStyle:{
    fontSize: 18
  },
  thumbnailStyle: {
    height: 100,
    width: 100,
    marginLeft: 10,
    marginRight: 10
  },
  thumbnailContainerStyle: {
    justifyContent: 'center',
    alignItems: 'center',
    flex: 1
  },
  imageStyle: {
    height: 300,
    width: null
  }
};

export default ListViewItem;
// export default ListItem;
