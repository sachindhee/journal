package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.entity.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;


import java.util.stream.Stream;


public class UserArqumentsSourse implements ArgumentsProvider {
    @Override
    @Disabled
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(User.builder().userName("Sayam").password("Sayam").build()),
                Arguments.of(User.builder().userName("rohan").password("rohan").build())

        );

    }
}
