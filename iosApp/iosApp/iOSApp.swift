import SwiftUI
import ComposeApp

@main
struct iOSApp: App {

    init() {
        _ = KoinKt.doInitKoin(driverFactory: DatabaseDriverFactory())
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
