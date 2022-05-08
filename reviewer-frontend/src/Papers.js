import { useState, useEffect } from 'react'
import styles from './Papers.module.css'
import { UnrelatedPannel } from './Unrelated.js'
import { ReviewedPannel } from './Reviewed.js'
import { ToReviewPannel } from './ToReview.js'


function Paper({ paperId, paper, onPaperDoubleClick, onPaperClick }) {
    return (
        <div 
        className={paper.id === paperId ? styles.paperDivSelected : styles.paperDiv}
        onDoubleClick={() => onPaperDoubleClick(paper.id)}
        onClick={() => onPaperClick(paper.id)} >
            <h2>{paper.title}</h2>
        </div>
    );
}

function PaperList({ paperId, papers, onPaperDoubleClick, onPaperClick }) {
    return (
        <div className={styles.paperListDiv}>
            {papers.length > 0 
            ?
            papers.map((paper, index) => <Paper 
                                          key={index} 
                                          paperId={paperId}
                                          paper={paper} 
                                          onPaperDoubleClick={onPaperDoubleClick}
                                          onPaperClick={onPaperClick} />)
            :
            <p>There aren't any papers to see here</p>}
        </div>
    );
}

function Placeholder() {
    return (
        <div className={styles.placeholder}>
        </div>
    );
}

function FunctionalityContainer({ option }) {
    return (
        <div className={styles.functionalityContainer}>
            {option === "all" && <Placeholder />}
            {option === "unrelated" && <UnrelatedPannel />}
            {option === "reviewed" && <ReviewedPannel />}
            {option === "toReview" && <ToReviewPannel />}
        </div>
    );
}

function AllPapersButton({ setOption }) {
    const allButtonValue = "all"

    const handleClick = () => {
        setOption(allButtonValue)
    };

    return <button className={styles.papersButton} onClick={handleClick} type="button">All Papers</button>
}

function ToReviewButton({ setOption }) {
    const toReviewButtonValue = "toReview";

    const handleClick = () => {
        setOption(toReviewButtonValue);
    }

    return <button className={styles.papersButton} onClick={handleClick} type="button">To review</button>
}

function ReviewedButton({ setOption }) {
    const reviewedButtonValue = "reviewed";

    const handleClick = () => {
        setOption(reviewedButtonValue);
    };

    return <button className={styles.papersButton} onClick={handleClick} type="button">Reviewed</button>
}

function UnrelatedButton({ setOption }) {
    const unrelatedButtonValue = "unrelated";

    const handleClick = () => {
        setOption(unrelatedButtonValue);
    };

    return <button className={styles.papersButton} onClick={handleClick} type="button">Unrelated</button>
}

function ViewPapers(props) {
    return (
        <div className={styles.viewPapers}>
            <AllPapersButton setOption={props.setOption} />
        </div>
    );
}

function FilterPapers(props) {
    return (
        <div className={styles.filterPapers}>
            <ToReviewButton setOption={props.setOption} />
            <ReviewedButton setOption={props.setOption} />
            <UnrelatedButton setOption={props.setOption} />
        </div>
    );
}

function ButtonGroup(props) {
    return (
        <div className={styles.buttonGroup}>
            <ViewPapers setOption={props.setOption} setContent={props.setContent} />
            <FilterPapers setOption={props.setOption} />
        </div>
    );
}


function ToggleArea(props) {
    let [option, setOption] = useState("all");

    return (
        <div className={styles.toggleArea}>
            <FunctionalityContainer option={option} />
            <div className={styles.secondarySeparator}></div>
            <ButtonGroup 
            setOption={setOption} 
            setContent={props.setContent}/>
        </div>
    );
}

function PapersContent({ setContent, paperId, papers, onPaperDoubleClick, onPaperClick }) {
    return (
        <div className={styles.contentArea}>
            <PaperList 
            paperId={paperId}
            papers={papers} 
            onPaperDoubleClick={onPaperDoubleClick} 
            onPaperClick={onPaperClick} />
            <ToggleArea setContent={setContent} />
        </div>
    )
}

function PapersPage({ setContent, paperId, setPaperId }) {

    const [ papers, setPapers ] = useState([])

    const fetchAllPapers = async () => {
        const response = await fetch(`http://localhost/SoftEngApplication/reviewer-backend/fetch-all-papers.php`)
        const data = await response.json()
        return Promise.resolve(data)
    }

    const getPapers = () => {
        fetchAllPapers().then((data) => setPapers(data));
    }

    useEffect(() => {
        getPapers()
    }, [])

    const onPaperDoubleClick = (newPaperId) => {
        setPaperId(newPaperId)
        setContent('selectedPage')
    }

    const onPaperClick = (newPaperId) => {
        setPaperId(newPaperId)
    }

    return (
        <div className={styles.papersArea}>
            <h1 className={styles.papersHeader}>Papers</h1>
            <div className={styles.mainSeparator}></div>
            <PapersContent 
            setContent={setContent} 
            paperId={paperId}
            papers={papers}
            onPaperDoubleClick={onPaperDoubleClick} 
            onPaperClick={onPaperClick} />
        </div>
    );
}

export { PapersPage }