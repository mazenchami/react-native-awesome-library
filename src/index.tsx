import { NativeModules, Platform } from 'react-native';
import { type Spec } from './NativeAwesomeContactsLibrary';

const LINKING_ERROR =
  `The package 'react-native-awesome-contacts-library' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

// @ts-expect-error
const isTurboModuleEnabled = global.__turboModuleProxy != null;

const AwesomeContactsLibraryModule = isTurboModuleEnabled
  ? require('./NativeAwesomeContactsLibrary').default
  : NativeModules.AwesomeContactsLibrary;

const AwesomeContactsLibrary = AwesomeContactsLibraryModule
  ? AwesomeContactsLibraryModule
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

export const TurboContacts = AwesomeContactsLibrary as Spec;
