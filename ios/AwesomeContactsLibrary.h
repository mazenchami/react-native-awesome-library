
#ifdef RCT_NEW_ARCH_ENABLED
#import "RNAwesomeContactsLibrarySpec.h"
#import <Contacts/Contacts.h>

@interface AwesomeContactsLibrary : NSObject <NativeAwesomeContactsLibrarySpec>
  @property CNContactStore *contactsStore;
#else
#import <React/RCTBridgeModule.h>
#import <Contacts/Contacts.h>

@interface AwesomeContactsLibrary : NSObject <RCTBridgeModule>
  @property CNContactStore *contactsStore;
#endif

@end
