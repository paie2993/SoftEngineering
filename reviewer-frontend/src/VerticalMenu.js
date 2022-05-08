import styles from './VerticalMenu.module.css'

function HomeButton() {
    return <button type="button" className={styles.homeButton}>Home</button>;
}   

function ConferencesButton() {
    return <button className={styles.optionsButton} type="button">Conferences</button>;
}

function AllPapersButton(props) {
    const allPapersButtonValue = "allPapers";

    const handleClick = () => {
        props.setContent(allPapersButtonValue);
    };

    return <button className={styles.optionsButton} onClick={handleClick} type="button">All Papers</button>;
}

function TopicsButton(props) {
    const topicsButtonValue = "topics";

    const handleClick = () => {
        props.setContent(topicsButtonValue);
    };

    return <button className={styles.optionsButton} onClick={handleClick} type="button">Topics</button>;
}

function PersonalInformationButton() {
    return <button className={styles.optionsButton} type="button">Personal Information</button>;
}

function OptionsPannel(props) {
    return (
        <div className={styles.optionsPannel}>
            <ConferencesButton />
            <AllPapersButton setContent={props.setContent}/>
            <TopicsButton setContent={props.setContent}/>
            <PersonalInformationButton />
        </div>
    );
}

function LogOutButton() {
    return <button className={styles.logOutButton} type="button">Log Out</button>;
}

function VerticalMenu(props) {
    return (
        <div className={styles.verticalMenu}>
            <HomeButton />
            <OptionsPannel setContent={props.setContent}/>
            <LogOutButton />
        </div>
    );
}

export { VerticalMenu }