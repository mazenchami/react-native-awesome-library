
#ifdef RCT_NEW_ARCH_ENABLED
#import "RNAwesomeContactsLibrarySpec.h"

@interface AwesomeContactsLibrary : NSObject <NativeAwesomeContactsLibrarySpec>
#else
#import <React/RCTBridgeModule.h>

@interface AwesomeContactsLibrary : NSObject <RCTBridgeModule>
#endif

@end
