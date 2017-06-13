/**
 * Created by andro on 6/14/17.
 */
function calcPassScore(elem)  {
    console.log(elem);
    let score = 0;
    let pass = elem;
    if (!pass)
        return score;

    let letters = {};
    for (let i=0; i<pass.length; i++) {
        letters[pass[i]] = (letters[pass[i]] || 0) + 1;
        score += 5.0 / letters[pass[i]];
    }

    let variations = {
        digits: /\d/.test(pass),
        lower: /[a-z]/.test(pass),
        upper: /[A-Z]/.test(pass),
        nonWords: /\W/.test(pass)
    };

    let variationCount = 0;
    for (let check in variations) {
        variationCount += (variations[check] === true) ? 1 : 0;
    }
    score += (variationCount - 1) * 10;
    score = (score/60)*100;
    return score;
}