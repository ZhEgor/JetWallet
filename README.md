# JetWallet
Wallet for storing credits cards. JetWallet's written in Compose with Material3

## Preview

### Theme
| Android SDK >= Api 31 | Android SDK < Api 31 |
| ------------- | ------------- |
| <image src="https://user-images.githubusercontent.com/41516868/209571127-03becc39-7fc5-4e00-8e3b-ff7ec3acd2df.gif" width="300"> | <image src="https://user-images.githubusercontent.com/41516868/209571180-3a53530e-2c55-4ef9-b353-c60f79c55708.gif" width="300"> |

### Add card feature
| Add a card manually | Add a card using a card recognizer |
| ------------- | ------------- |
| <image src="https://user-images.githubusercontent.com/41516868/209571553-2a0cc3a3-e147-45ba-9da1-50df07328b02.gif" width="300"> | <image src="https://user-images.githubusercontent.com/41516868/209571902-249693f7-3222-4ca7-8531-5a6cb718d980.gif" width="300"> |

### Special features
| Rotating | Card skin |
| ------------- | ------------- |
| <image src="https://user-images.githubusercontent.com/41516868/209571799-ee1c7c9d-c87d-4a55-b4e8-86abd9dd2594.gif" width="300"> | <image src="https://user-images.githubusercontent.com/41516868/209571962-957632ad-a86d-4d78-8640-54778a96f860.gif" width="300"> |

## Tech stack

- Compose
- Material 3
- MVI
- Multi-moduling
- GradleKts
- Koin
- Coroutines
- Room
- SqlCipher
- Google MLKit
- CameraX
- Coil

## Development subtleties

### Storing data:
- We store card details encrypted using SqlCipher in Room database, to store a password for encryption, we use KeyStore.

### Multi-moduling:
The app has structure:
- app
- core
  - common
  - navigation
  - theme
  - components
- data
- domain
- features
  - mywallet
  - cardrecognition
- buildSrc

### UI: 
- The app supports **DarkTheme**, **LightTheme** and **DynamicTheme**.

- Below you can see genereted compose metrics report for **:features:mywallet** module, for more information on how to get this report follow the [link](https://github.com/androidx/androidx/blob/08c6116/compose/compiler/design/compiler-metrics.md).

<details> 
  <summary>mywallet_debug-composables.txt</summary>
  <pre>
    <code>
restartable skippable scheme("[androidx.compose.ui.UiComposable]") fun CardCarouselRoute()
restartable skippable scheme("[androidx.compose.ui.UiComposable]") fun CardCarouselScreen(
  stable viewModel: CardCarouselViewModel
)
restartable skippable scheme("[androidx.compose.ui.UiComposable]") fun CardsAreLoaded(
  stable viewModel: CardCarouselViewModel
  stable state: Loaded
)
restartable skippable scheme("[androidx.compose.ui.UiComposable]") fun CardsAreLoading()
restartable skippable scheme("[androidx.compose.ui.UiComposable]") fun CardsAreEmpty()
restartable skippable scheme("[androidx.compose.ui.UiComposable]") fun CardCarouselScreenPreview()
restartable skippable scheme("[androidx.compose.ui.UiComposable]") fun AddCardButton(
  stable <this>: BoxScope
  stable onClick: Function0<Unit>
)
restartable skippable scheme("[androidx.compose.ui.UiComposable]") fun BankCard(
  stable card: BankCard
  stable onCopy: Function1<String, Unit>
  stable onEditClick: Function1<BankCard, Unit>
  stable onDeleteClick: Function1<BankCard, Unit>
)
restartable skippable scheme("[androidx.compose.ui.UiComposable]") fun EditableBankCard(
  stable bankCard: BankCard
  stable isFrontSide: Boolean
  stable onPhotoUriChange: Function1<Uri?, Unit>
)
restartable skippable scheme("[androidx.compose.ui.UiComposable]") fun PickSkinButton(
  stable modifier: Modifier? = @static Companion
  stable onClick: Function0<Unit>
)
restartable skippable scheme("[androidx.compose.ui.UiComposable]") fun DeleteSkinButton(
  stable modifier: Modifier? = @static Companion
  stable onClick: Function0<Unit>
)
restartable skippable scheme("[androidx.compose.ui.UiComposable]") fun ShimmeringBankCard()
restartable skippable scheme("[androidx.compose.ui.UiComposable]") fun ImmutableBackSideCard(
  stable bankCard: BankCard
)
restartable skippable scheme("[androidx.compose.ui.UiComposable]") fun ImmutableBackSideCardPreview()
restartable skippable scheme("[androidx.compose.ui.UiComposable]") fun ShimmeringBackSideCard()
restartable skippable scheme("[androidx.compose.ui.UiComposable]") fun ShimmeringBackSideCardPreview()
restartable skippable scheme("[androidx.compose.ui.UiComposable, [androidx.compose.ui.UiComposable]]") fun StatelessBackSideCard(
  stable modifier: Modifier? = @static Companion
  stable verificationNumberContent: Function2<Composer, Int, Unit>
)
restartable skippable scheme("[androidx.compose.ui.UiComposable]") fun AuthorizedSignatureStripe(
  stable modifier: Modifier? = @static Companion
)
restartable skippable scheme("[androidx.compose.ui.UiComposable]") fun MagneticStripe(
  stable modifier: Modifier? = @static Companion
)
restartable skippable scheme("[androidx.compose.ui.UiComposable, [androidx.compose.ui.UiComposable]]") fun SignatureAndVerificationNumberRow(
  stable modifier: Modifier? = @static Companion
  stable verificationNumberContent: Function2<Composer, Int, Unit>
)
restartable skippable scheme("[androidx.compose.ui.UiComposable, [androidx.compose.ui.UiComposable]]") fun VerificationNumberContainer(
  stable modifier: Modifier? = @static Companion
  stable content: Function2<Composer, Int, Unit>
)
restartable skippable scheme("[androidx.compose.ui.UiComposable]") fun VerificationNumberText(
  stable modifier: Modifier? = @static Companion
  stable number: String
)
restartable skippable scheme("[androidx.compose.ui.UiComposable, [androidx.compose.ui.UiComposable]]") fun CardTextContainer(
  stable modifier: Modifier? = @static Companion
  stable content: Function2<Composer, Int, Unit>
)
restartable skippable scheme("[androidx.compose.ui.UiComposable]") fun EmptyBankCard(
  stable modifier: Modifier? = @static Companion
)
restartable skippable scheme("[androidx.compose.ui.UiComposable, [androidx.compose.ui.UiComposable]]") fun ImmutableFrontSideCard(
  stable bankCard: BankCard
  stable toolsContent: @[ExtensionFunctionType] Function3<RowScope, Composer, Int, Unit>? = @static null
)
restartable skippable scheme("[androidx.compose.ui.UiComposable]") fun FrontSideCardPreview()
restartable skippable scheme("[androidx.compose.ui.UiComposable]") fun ShimmeringFrontSideCard()
restartable skippable scheme("[androidx.compose.ui.UiComposable]") fun ShimmeringFrontSideCardPreview()
restartable skippable scheme("[androidx.compose.ui.UiComposable, [androidx.compose.ui.UiComposable], [androidx.compose.ui.UiComposable], [androidx.compose.ui.UiComposable], [androidx.compose.ui.UiComposable]]") fun StatelessFrontSideCard(
  stable modifier: Modifier? = @static Companion
  stable cardNumberContent: Function2<Composer, Int, Unit>
  stable expirationDateContent: Function2<Composer, Int, Unit>
  stable cardholderContent: Function2<Composer, Int, Unit>
  stable skinContent: Function2<Composer, Int, Unit>? = @static null
)
restartable skippable scheme("[androidx.compose.ui.UiComposable, [androidx.compose.ui.UiComposable]]") fun CardNumberContainer(
  stable modifier: Modifier? = @static Companion
  stable content: Function2<Composer, Int, Unit>
)
restartable skippable scheme("[androidx.compose.ui.UiComposable]") fun CardNumberText(
  stable modifier: Modifier? = @static Companion
  stable number: String
)
restartable skippable scheme("[androidx.compose.ui.UiComposable, [androidx.compose.ui.UiComposable]]") fun CardholderContainer(
  stable modifier: Modifier? = @static Companion
  stable content: Function2<Composer, Int, Unit>
)
restartable skippable scheme("[androidx.compose.ui.UiComposable]") fun CardholderText(
  stable modifier: Modifier? = @static Companion
  stable cardholder: String
)
restartable skippable scheme("[androidx.compose.ui.UiComposable, [androidx.compose.ui.UiComposable]]") fun ExpirationDateContainer(
  stable modifier: Modifier? = @static Companion
  stable content: Function2<Composer, Int, Unit>
)
restartable skippable scheme("[androidx.compose.ui.UiComposable]") fun ExpirationDateText(
  stable modifier: Modifier? = @static Companion
  stable expirationDate: String
)
restartable skippable scheme("[androidx.compose.ui.UiComposable, [androidx.compose.ui.UiComposable]]") fun SkinContainer(
  stable modifier: Modifier? = @static Companion
  stable content: Function2<Composer, Int, Unit>
)
restartable skippable scheme("[androidx.compose.ui.UiComposable, [androidx.compose.ui.UiComposable]]") fun ToolsContainer(
  stable <this>: BoxScope
  stable modifier: Modifier? = @static Companion
  stable toolsContent: @[ExtensionFunctionType] Function3<RowScope, Composer, Int, Unit>? = @static null
)
restartable skippable scheme("[androidx.compose.ui.UiComposable, [androidx.compose.ui.UiComposable]]") fun RotatingCardWrapper(
  stable modifier: Modifier? = @static Companion
  stable initialIsFrontSide: Boolean = @dynamic LiveLiterals$RotatingCardWrapperKt.Boolean$param-initialIsFrontSide$fun-RotatingCardWrapper()
  stable onLongPress: Function1<@[ParameterName(name = 'isFront')] Boolean, Unit>? = @static null
  stable cardContent: Function3<@[ParameterName(name = 'isFrontSide')] Boolean, Composer, Int, Unit>
)
restartable skippable scheme("[androidx.compose.ui.UiComposable]") fun AddCardPopUp(
  popUpState: PopUpState
  stable onHide: Function0<Unit>
  stable onAddClick: Function1<BankCard, Unit>
)
restartable skippable scheme("[androidx.compose.ui.UiComposable]") fun EditCardPopUp(
  popUpState: PopUpState
  stable bankCard: BankCard
  stable onHide: Function0<Unit>
  stable onSaveClick: Function1<BankCard, Unit>
)
restartable skippable scheme("[androidx.compose.ui.UiComposable]") fun CardRecognizerPage(
  stable modifier: Modifier? = @static Companion
  stable forceToLeaveComposition: Boolean
  stable onReturnToPreviousPage: Function0<Unit>
  stable onFrontSideCardRecognized: Function2<@[ParameterName(name = 'cardNumber')] String, @[ParameterName(name = 'expirationDate')] String, Unit>
  stable onBackSideCardRecognized: Function1<@[ParameterName(name = 'verificationNumber')] String, Unit>
)
restartable skippable scheme("[androidx.compose.ui.UiComposable]") fun EditableCardPage(
  stable modifier: Modifier? = @static Companion
  stable bankCard: BankCard
  stable editBankCardCallbacks: EditBankCardCallbacks
  stable onDone: Function0<Unit>
)
    </code>
  </pre>
</details>

<details> 
  <summary>mywallet_debug-classes.txt</summary>
  <pre>
  <code>
stable class LoadCards {
  <runtime stability> = Stable
}
stable class LoadedCards {
  stable val cards: StableList<BankCard>
  <runtime stability> = Stable
}
stable class FailedToLoadCards {
  stable val errorMessage: String?
  <runtime stability> = Stable
}
stable class ChangeStateOfAddPopUp {
  stable val show: Boolean
  <runtime stability> = Stable
}
stable class ChangeStateOfEditPopUp {
  stable val show: Boolean
  stable val cardToEdit: BankCard?
  <runtime stability> = Stable
}
stable class AddCard {
  stable val bankCard: BankCard
  <runtime stability> = Stable
}
stable class AddedCardSuccessfully {
  <runtime stability> = Stable
}
stable class SaveCard {
  stable val bankCard: BankCard
  <runtime stability> = Stable
}
stable class SavedCardSuccessfully {
  <runtime stability> = Stable
}
stable class DeleteCard {
  stable val bankCard: BankCard
  <runtime stability> = Stable
}
stable class DeletedCardSuccessfully {
  <runtime stability> = Stable
}
stable class Loading {
  <runtime stability> = Stable
}
stable class Loaded {
  stable val cards: StableList<BankCard>
  <runtime stability> = Stable
}
stable class Empty {
  <runtime stability> = Stable
}
</code>
</pre>
</details>

<details> 
  <summary>mywallet_debug-module.json</summary>
  <pre>
    <code>
{
 "skippableComposables": 91,
 "restartableComposables": 91,
 "readonlyComposables": 0,
 "totalComposables": 91,
 "restartGroups": 91,
 "totalGroups": 108,
 "staticArguments": 67,
 "certainArguments": 66,
 "knownStableArguments": 617,
 "knownUnstableArguments": 3,
 "unknownStableArguments": 8,
 "totalArguments": 628,
 "markedStableClasses": 0,
 "inferredStableClasses": 14,
 "inferredUnstableClasses": 0,
 "inferredUncertainClasses": 0,
 "effectivelyStableClasses": 14,
 "totalClasses": 14,
 "memoizedLambdas": 64,
 "singletonLambdas": 0,
 "singletonComposableLambdas": 20,
 "composableLambdas": 37,
 "totalLambdas": 72
}
    </code>
  </pre>
</details>

<details> 
  <summary>mywallet_debug-composables.csv</summary>
  <pre>
    <code>
package,name,composable,skippable,restartable,readonly,inline,isLambda,hasDefaults,defaultsGroup,groups,calls,
com.zhiroke.features.mywallet.presentation.cardcarousel.CardCarouselRoute,CardCarouselRoute,1,1,1,0,0,0,0,0,1,2,
com.zhiroke.features.mywallet.presentation.cardcarousel.CardCarouselScreen,CardCarouselScreen,1,1,1,0,0,0,0,0,1,2,
com.zhiroke.features.mywallet.presentation.cardcarousel.CardsAreLoaded,CardsAreLoaded,1,1,1,0,0,0,0,0,1,2,
com.zhiroke.features.mywallet.presentation.cardcarousel.CardsAreLoading,CardsAreLoading,1,1,1,0,0,0,0,0,1,1,
com.zhiroke.features.mywallet.presentation.cardcarousel.CardsAreEmpty,CardsAreEmpty,1,1,1,0,0,0,0,0,1,1,
com.zhiroke.features.mywallet.presentation.cardcarousel.CardCarouselScreenPreview,CardCarouselScreenPreview,1,1,1,0,0,0,0,0,1,1,
com.zhiroke.features.mywallet.presentation.cardcarousel.components.button.AddCardButton,AddCardButton,1,1,1,0,0,0,0,0,1,5,
com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.BankCard,BankCard,1,1,1,0,0,0,0,0,1,3,
com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.EditableBankCard,EditableBankCard,1,1,1,0,0,0,0,0,1,3,
com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.PickSkinButton,PickSkinButton,1,1,1,0,0,0,0,0,1,1,
com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.DeleteSkinButton,DeleteSkinButton,1,1,1,0,0,0,0,0,1,1,
com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.ShimmeringBankCard,ShimmeringBankCard,1,1,1,0,0,0,0,0,1,1,
com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.back.ImmutableBackSideCard,ImmutableBackSideCard,1,1,1,0,0,0,0,0,1,2,
com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.back.ImmutableBackSideCardPreview,ImmutableBackSideCardPreview,1,1,1,0,0,0,0,0,1,1,
com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.back.ShimmeringBackSideCard,ShimmeringBackSideCard,1,1,1,0,0,0,0,0,1,2,
com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.back.ShimmeringBackSideCardPreview,ShimmeringBackSideCardPreview,1,1,1,0,0,0,0,0,1,1,
com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.back.StatelessBackSideCard,StatelessBackSideCard,1,1,1,0,0,0,0,0,1,1,
com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.back.components.AuthorizedSignatureStripe,AuthorizedSignatureStripe,1,1,1,0,0,0,0,0,1,1,
com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.back.components.MagneticStripe,MagneticStripe,1,1,1,0,0,0,0,0,1,1,
com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.back.components.SignatureAndVerificationNumberRow,SignatureAndVerificationNumberRow,1,1,1,0,0,0,0,0,1,1,
com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.back.components.VerificationNumberContainer,VerificationNumberContainer,1,1,1,0,0,0,0,0,1,1,
com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.back.components.VerificationNumberText,VerificationNumberText,1,1,1,0,0,0,0,0,1,1,
com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.common.CardTextContainer,CardTextContainer,1,1,1,0,0,0,0,0,1,3,
com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.front.EmptyBankCard,EmptyBankCard,1,1,1,0,0,0,0,0,1,1,
com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.front.ImmutableFrontSideCard,ImmutableFrontSideCard,1,1,1,0,0,0,0,0,1,2,
com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.front.FrontSideCardPreview,FrontSideCardPreview,1,1,1,0,0,0,0,0,1,1,
com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.front.ShimmeringFrontSideCard,ShimmeringFrontSideCard,1,1,1,0,0,0,0,0,1,2,
com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.front.ShimmeringFrontSideCardPreview,ShimmeringFrontSideCardPreview,1,1,1,0,0,0,0,0,1,1,
com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.front.StatelessFrontSideCard,StatelessFrontSideCard,1,1,1,0,0,0,0,0,1,1,
com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.front.components.CardNumberContainer,CardNumberContainer,1,1,1,0,0,0,0,0,1,1,
com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.front.components.CardNumberText,CardNumberText,1,1,1,0,0,0,0,0,1,1,
com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.front.components.CardholderContainer,CardholderContainer,1,1,1,0,0,0,0,0,1,1,
com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.front.components.CardholderText,CardholderText,1,1,1,0,0,0,0,0,1,2,
com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.front.components.ExpirationDateContainer,ExpirationDateContainer,1,1,1,0,0,0,0,0,1,1,
com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.front.components.ExpirationDateText,ExpirationDateText,1,1,1,0,0,0,0,0,1,2,
com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.front.components.SkinContainer,SkinContainer,1,1,1,0,0,0,0,0,1,1,
com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.front.components.ToolsContainer,ToolsContainer,1,1,1,0,0,0,0,0,1,1,
com.zhiroke.features.mywallet.presentation.cardcarousel.components.cardwrapper.RotatingCardWrapper,RotatingCardWrapper,1,1,1,0,0,0,1,0,2,8,
com.zhiroke.features.mywallet.presentation.cardcarousel.components.popups.AddCardPopUp,AddCardPopUp,1,1,1,0,0,0,0,0,1,4,
com.zhiroke.features.mywallet.presentation.cardcarousel.components.popups.EditCardPopUp,EditCardPopUp,1,1,1,0,0,0,0,0,1,2,
com.zhiroke.features.mywallet.presentation.cardcarousel.components.popups.components.CardRecognizerPage,CardRecognizerPage,1,1,1,0,0,0,0,0,1,1,
com.zhiroke.features.mywallet.presentation.cardcarousel.components.popups.components.EditableCardPage,EditableCardPage,1,1,1,0,0,0,0,0,1,3,
    </code>
  </pre>
</details>
