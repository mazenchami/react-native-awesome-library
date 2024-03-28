#import "AwesomeContactsLibrary.h"

@implementation AwesomeContactsLibrary
RCT_EXPORT_MODULE()

- (instancetype)init {
    self = [super init];
    if (self) {
        self.contactsStore = [[CNContactStore alloc] init];
    }
    return self;
}

+ (BOOL)requiresMainQueueSetup
{
  return NO;
}

RCT_EXPORT_BLOCKING_SYNCHRONOUS_METHOD(hasContactsPermission) {
    CNAuthorizationStatus authorizationStatus = [CNContactStore authorizationStatusForEntityType:CNEntityTypeContacts];

    return @(authorizationStatus == CNAuthorizationStatusAuthorized);
}

RCT_EXPORT_METHOD(requestContactsPermission:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject) {
    [self.contactsStore requestAccessForEntityType:CNEntityTypeContacts completionHandler:^(BOOL granted, NSError *error) {
       if (error) {
            return reject(@"Error", @"An Error occurred while requesting permission", error);
       }
       resolve(@(granted));
    }];
}

// Don't compile this code when we build for the old architecture.
#ifdef RCT_NEW_ARCH_ENABLED
- (std::shared_ptr<facebook::react::TurboModule>)getTurboModule:
    (const facebook::react::ObjCTurboModule::InitParams &)params
{
    return std::make_shared<facebook::react::NativeAwesomeContactsLibrarySpecJSI>(params);
}
#endif

@end
