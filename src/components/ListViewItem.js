import React, { Component } from 'react';
import { Text, TouchableWithoutFeedback, View, Image, Linking} from 'react-native';
import { Card } from 'react-native-elements'
class ListViewItem extends Component {
  pressCard(){
    // alert("boom");
    this.props.method();
  }

  renderRowText(){
    if(this.props.item != null && this.props.item.title != null) {
      return <View style={{ flexDirection: 'column' }}>
          <Text style={{ fontSize: 14, color: 'black', flexWrap: "wrap"}}>{this.props.item.title}</Text>
          <Text style={{ color: 'black' }}>{this.props.item.time} </Text>
        </View>;
    }
  }
  render() {
    return (
      <TouchableWithoutFeedback
        //  onPress={() => Linking.openURL("https://www.instagram.com/_u/" + album.user.username)}
        style={{  height: 120 }}
         onPress={() => this.pressCard()}
        >
          <View style={{  height: 120, flexDirection: 'row', alignItems: 'center', flex: 1, backgroundColor: 'white', margin: 10 }}>
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
    fontSize: 18,
    color: 'white'
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
