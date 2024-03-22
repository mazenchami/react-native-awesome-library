import * as React from 'react';

import { StyleSheet, View, Text, Pressable } from 'react-native';
import { TurboContacts } from 'react-native-awesome-contacts-library';

export default function App() {
  const [hasPermission, setHasPermission] = React.useState<boolean>(
    TurboContacts.hasContactsPermission()
  );

  return (
    <View style={styles.container}>
      <Text>TurboContacts Permission Status: {String(hasPermission)}</Text>
      {!TurboContacts.hasContactsPermission() && (
        <Pressable
          onPress={() => {
            TurboContacts.requestContactsPermission().then(setHasPermission);
          }}
        >
          <Text>Request permission</Text>
        </Pressable>
      )}
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
