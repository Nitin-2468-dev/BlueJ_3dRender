# Enhanced Clinical Rule Generation System

## Overview

The `enhanced_clinical_gen.py` script provides comprehensive clinical decision support with the ability to generate and analyze all possible symptom combinations. This system can analyze over 2.6 million different symptom patterns to provide medical diagnosis recommendations.

## Features

### 🩺 Clinical Analysis Engine
- **90 symptom database** with comprehensive medical symptom coverage
- **Confidence-based diagnosis scoring** with weighted symptom factors
- **Differential diagnosis ranking** with multiple diagnostic possibilities
- **Treatment recommendation system** tailored to specific diagnoses

### 🔍 Comprehensive Symptom Analysis
- **Generate all possible combinations** of symptoms with lengths 2, 3, and 4
- **4,005 two-symptom combinations**
- **117,480 three-symptom combinations** 
- **2,555,190 four-symptom combinations**
- **2,676,675 total combinations** across all lengths

### ⚕️ Clinical Decision Support
- **Urgency level assessment** (Emergency/Urgent/Routine) with reasoning
- **Red flag detection** for critical warning signs
- **Symptom validation** against known medical symptom database
- **Comprehensive analysis workflow** from symptom input to treatment recommendations

## Usage

### Demo Mode (Original Functionality)
```bash
python enhanced_clinical_gen.py --demo
```
Shows sample analysis with predefined symptoms: fever, cough, fatigue, shortness_of_breath

### Generate Symptom Combinations

#### Two-symptom combinations
```bash
python enhanced_clinical_gen.py --combo-length=2 --max-rules=50
```

#### Three-symptom combinations (default)
```bash
python enhanced_clinical_gen.py --combo-length=3 --max-rules=100
```

#### Four-symptom combinations
```bash
python enhanced_clinical_gen.py --combo-length=4 --max-rules=20
```

#### All combination lengths (2, 3, and 4)
```bash
python enhanced_clinical_gen.py --combo-length=all --max-rules=100
```

#### Generate unlimited rules (process all combinations)
```bash
python enhanced_clinical_gen.py --combo-length=3 --max-rules=0
```

### Command-Line Options

| Option | Description | Default | Valid Values |
|--------|-------------|---------|--------------|
| `--demo` | Run in demo mode | False | N/A |
| `--combo-length` | Length of symptom combinations | 3 | 2, 3, 4, "all" |
| `--max-rules` | Maximum number of rules to generate | 300 | 0 (unlimited) or positive integer |

### Help
```bash
python enhanced_clinical_gen.py --help
```

## Examples

### Example 1: Quick Analysis of 2-symptom patterns
```bash
python enhanced_clinical_gen.py --combo-length=2 --max-rules=10
```

Output:
```
=== Enhanced Clinical Rule Generation ===
Engine initialized with 90 symptoms
Generating combinations of length: 2
Maximum rules to generate: 10

Generated 4005 combinations of length 2
Selected 10 combinations (limited by max_rules)

=== Analyzing Symptom Combinations ===

Rule 1: Analyzing symptoms: difficulty_breathing, coordination_problems
  → Primary Diagnosis: Viral Syndrome (Confidence: 0.26)
  → Alternatives: Pneumonia (0.24), Influenza (0.23)
  → Urgency: Emergency
  → Red Flags: Respiratory or cardiac concern, Neurological concern
```

### Example 2: Comprehensive 3-symptom analysis
```bash
python enhanced_clinical_gen.py --combo-length=3 --max-rules=5
```

### Example 3: All combination lengths with limited output
```bash
python enhanced_clinical_gen.py --combo-length=all --max-rules=20
```

## Clinical Diagnoses Supported

The system can identify and rank the following medical conditions:

- **Viral Syndrome** - General viral infections
- **Influenza** - Seasonal flu
- **Common Cold** - Upper respiratory viral infection
- **Upper Respiratory Infection** - Bacterial/viral upper respiratory tract infection
- **Pneumonia** - Lung infection
- **Bronchitis** - Bronchial tube inflammation
- **Gastroenteritis** - Stomach and intestinal inflammation
- **Migraine** - Severe headache disorder

## Red Flag Detection

The system automatically detects critical warning signs:

- **Respiratory or cardiac concern** - Breathing, chest pain, heart issues
- **Neurological concern** - Brain, nerve, coordination issues  
- **Gastrointestinal emergency** - Serious digestive system problems
- **Infection concern** - Signs of serious bacterial infections

## Urgency Levels

- **Emergency** - Immediate medical attention required
- **Urgent** - Prompt medical care recommended
- **Routine** - Standard medical consultation appropriate

## Technical Details

### Symptom Database
The system includes 90 medical symptoms across categories:
- Common symptoms (fever, cough, fatigue, etc.)
- Respiratory symptoms (wheezing, shortness of breath, etc.)
- Cardiovascular symptoms (chest pain, palpitations, etc.)
- Neurological symptoms (confusion, seizures, etc.)
- Gastrointestinal symptoms (nausea, abdominal pain, etc.)
- Skin symptoms (rash, itching, etc.)
- Other symptoms (urinary, mood, etc.)

### Combination Mathematics
- **2 symptoms**: C(90,2) = 4,005 combinations
- **3 symptoms**: C(90,3) = 117,480 combinations  
- **4 symptoms**: C(90,4) = 2,555,190 combinations
- **All lengths**: 2,676,675 total combinations

## Performance Considerations

- **2-symptom analysis**: Very fast, suitable for comprehensive analysis
- **3-symptom analysis**: Moderate speed, good for detailed exploration
- **4-symptom analysis**: Slower, recommend limiting max-rules
- **All combinations**: Use max-rules parameter to control processing time

## Error Handling

The system includes robust error handling:
- Invalid combo-length values are rejected
- Symptom validation against known medical terms
- Graceful handling of edge cases
- Clear error messages for user guidance

## Dependencies

- Python 3.6+
- Standard library modules only (argparse, itertools, random, re, typing)

## Compatibility

- Maintains full backwards compatibility with original demo functionality
- Kaggle environment compatible
- Cross-platform support (Windows, macOS, Linux)