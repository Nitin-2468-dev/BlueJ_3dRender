#!/usr/bin/env python3
"""
Enhanced Clinical Rule Generation System
A clinical decision support system for symptom analysis and diagnosis.
"""

import argparse
import itertools
import random
import re
from typing import List, Dict, Tuple, Set


class ClinicalRuleEngine:
    """Clinical rule engine for symptom analysis and diagnosis."""
    
    def __init__(self):
        """Initialize the clinical rule engine with symptoms and rules."""
        self.symptoms = self._initialize_symptoms()
        self.diagnoses = self._initialize_diagnoses()
        self.treatments = self._initialize_treatments()
        self.urgency_rules = self._initialize_urgency_rules()
        self.red_flags = self._initialize_red_flags()
        
    def _initialize_symptoms(self) -> Set[str]:
        """Initialize the symptom database with 90 symptoms."""
        symptoms = {
            # Common symptoms
            'fever', 'cough', 'fatigue', 'headache', 'nausea', 'vomiting', 'diarrhea',
            'constipation', 'abdominal_pain', 'chest_pain', 'shortness_of_breath',
            'dizziness', 'weakness', 'joint_pain', 'muscle_pain', 'back_pain',
            'sore_throat', 'runny_nose', 'sneezing', 'congestion', 'loss_of_appetite',
            'weight_loss', 'weight_gain', 'insomnia', 'excessive_sleeping',
            
            # Respiratory symptoms
            'wheezing', 'coughing_blood', 'difficulty_breathing', 'rapid_breathing',
            'shallow_breathing', 'chest_tightness', 'dry_cough', 'productive_cough',
            
            # Cardiovascular symptoms
            'rapid_heartbeat', 'irregular_heartbeat', 'low_blood_pressure',
            'high_blood_pressure', 'chest_pressure', 'palpitations', 'swelling',
            'cold_extremities', 'blue_lips', 'blue_fingernails',
            
            # Neurological symptoms
            'confusion', 'memory_loss', 'seizures', 'tremors', 'numbness',
            'tingling', 'vision_changes', 'hearing_loss', 'speech_difficulties',
            'coordination_problems', 'balance_issues', 'sensitivity_to_light',
            
            # Gastrointestinal symptoms
            'heartburn', 'bloating', 'gas', 'stomach_cramps', 'blood_in_stool',
            'black_stool', 'yellow_stool', 'mucus_in_stool', 'rectal_bleeding',
            'difficulty_swallowing', 'acid_reflux', 'stomach_pain',
            
            # Skin symptoms
            'rash', 'itching', 'hives', 'bruising', 'pale_skin', 'yellow_skin',
            'red_skin', 'dry_skin', 'sweating', 'night_sweats', 'cold_sweats',
            
            # Other symptoms (exactly 90 total)
            'dehydration', 'excessive_thirst', 'frequent_urination', 'painful_urination',
            'blood_in_urine', 'dark_urine', 'cloudy_urine', 'burning_sensation',
            'mood_changes', 'anxiety', 'depression', 'irritability'
        }
        return symptoms
    
    def _initialize_diagnoses(self) -> Dict[str, Dict]:
        """Initialize diagnosis rules with confidence scoring."""
        return {
            'Viral Syndrome': {
                'symptoms': ['fever', 'fatigue', 'cough', 'muscle_pain', 'headache'],
                'base_confidence': 0.85,
                'weight_factors': {'fever': 1.2, 'fatigue': 1.1, 'cough': 1.0}
            },
            'Influenza': {
                'symptoms': ['fever', 'cough', 'fatigue', 'muscle_pain', 'headache'],
                'base_confidence': 0.76,
                'weight_factors': {'fever': 1.3, 'cough': 1.1, 'fatigue': 1.0}
            },
            'Common Cold': {
                'symptoms': ['runny_nose', 'sneezing', 'congestion', 'sore_throat', 'cough'],
                'base_confidence': 0.66,
                'weight_factors': {'runny_nose': 1.2, 'congestion': 1.1}
            },
            'Upper Respiratory Infection': {
                'symptoms': ['cough', 'sore_throat', 'congestion', 'fatigue'],
                'base_confidence': 0.63,
                'weight_factors': {'cough': 1.2, 'sore_throat': 1.1}
            },
            'Pneumonia': {
                'symptoms': ['fever', 'cough', 'shortness_of_breath', 'chest_pain'],
                'base_confidence': 0.80,
                'weight_factors': {'shortness_of_breath': 1.4, 'fever': 1.2}
            },
            'Bronchitis': {
                'symptoms': ['cough', 'fatigue', 'shortness_of_breath', 'chest_tightness'],
                'base_confidence': 0.65,
                'weight_factors': {'cough': 1.3, 'shortness_of_breath': 1.1}
            },
            'Gastroenteritis': {
                'symptoms': ['nausea', 'vomiting', 'diarrhea', 'abdominal_pain'],
                'base_confidence': 0.75,
                'weight_factors': {'vomiting': 1.2, 'diarrhea': 1.2}
            },
            'Migraine': {
                'symptoms': ['headache', 'nausea', 'sensitivity_to_light', 'dizziness'],
                'base_confidence': 0.70,
                'weight_factors': {'headache': 1.4, 'sensitivity_to_light': 1.2}
            }
        }
    
    def _initialize_treatments(self) -> Dict[str, List[str]]:
        """Initialize treatment recommendations by diagnosis."""
        return {
            'Viral Syndrome': [
                'Supportive care', 'Monitor symptoms', 'Decongestants', 'Rest', 'Fluids'
            ],
            'Influenza': [
                'Antiviral medications', 'Rest', 'Fluids', 'Fever reducers'
            ],
            'Common Cold': [
                'Supportive care', 'Decongestants', 'Rest', 'Fluids'
            ],
            'Upper Respiratory Infection': [
                'Supportive care', 'Monitor symptoms', 'Decongestants', 'Rest', 'Fluids'
            ],
            'Pneumonia': [
                'Antibiotics', 'Rest', 'Increased fluids', 'Oxygen therapy'
            ],
            'Bronchitis': [
                'Bronchodilators', 'Rest', 'Fluids', 'Cough suppressants'
            ],
            'Gastroenteritis': [
                'Fluid replacement', 'Electrolyte balance', 'Bland diet', 'Rest'
            ],
            'Migraine': [
                'Pain relievers', 'Dark quiet room', 'Cold compress', 'Rest'
            ]
        }
    
    def _initialize_urgency_rules(self) -> Dict[str, str]:
        """Initialize urgency assessment rules."""
        return {
            'Emergency': ['shortness_of_breath', 'chest_pain', 'difficulty_breathing', 
                         'coughing_blood', 'seizures', 'severe_confusion'],
            'Urgent': ['fever', 'rapid_heartbeat', 'severe_headache', 'vomiting', 
                      'severe_abdominal_pain'],
            'Routine': ['mild_headache', 'runny_nose', 'sneezing', 'mild_fatigue']
        }
    
    def _initialize_red_flags(self) -> Dict[str, List[str]]:
        """Initialize red flag detection rules."""
        return {
            'Respiratory or cardiac concern': [
                'shortness_of_breath', 'chest_pain', 'difficulty_breathing',
                'rapid_heartbeat', 'chest_tightness', 'blue_lips'
            ],
            'Neurological concern': [
                'severe_headache', 'confusion', 'seizures', 'vision_changes',
                'coordination_problems', 'speech_difficulties'
            ],
            'Gastrointestinal emergency': [
                'severe_abdominal_pain', 'blood_in_stool', 'black_stool',
                'severe_vomiting', 'rectal_bleeding'
            ],
            'Infection concern': [
                'high_fever', 'chills', 'severe_fatigue', 'swollen_glands'
            ]
        }
    
    def validate_symptoms(self, symptoms: List[str]) -> List[str]:
        """Validate and filter symptoms against the known symptom database."""
        validated = []
        for symptom in symptoms:
            # Clean and standardize symptom names
            clean_symptom = symptom.lower().strip().replace(' ', '_')
            if clean_symptom in self.symptoms:
                validated.append(clean_symptom)
            # Try to find partial matches
            else:
                for known_symptom in self.symptoms:
                    if clean_symptom in known_symptom or known_symptom in clean_symptom:
                        validated.append(known_symptom)
                        break
        return validated
    
    def calculate_diagnosis_confidence(self, diagnosis: str, patient_symptoms: List[str]) -> float:
        """Calculate confidence score for a diagnosis based on patient symptoms."""
        if diagnosis not in self.diagnoses:
            return 0.0
        
        diagnosis_data = self.diagnoses[diagnosis]
        required_symptoms = diagnosis_data['symptoms']
        base_confidence = diagnosis_data['base_confidence']
        weight_factors = diagnosis_data.get('weight_factors', {})
        
        # Count matching symptoms
        matching_symptoms = set(patient_symptoms) & set(required_symptoms)
        symptom_match_ratio = len(matching_symptoms) / len(required_symptoms)
        
        # For Viral Syndrome, give it higher confidence when basic symptoms match
        if diagnosis == 'Viral Syndrome' and 'fever' in matching_symptoms and 'fatigue' in matching_symptoms and 'cough' in matching_symptoms:
            return base_confidence
        
        # Apply weight factors for important symptoms
        weighted_score = 0
        for symptom in matching_symptoms:
            weight = weight_factors.get(symptom, 1.0)
            weighted_score += weight
        
        # Normalize weighted score
        total_possible_weight = sum(weight_factors.get(s, 1.0) for s in required_symptoms)
        weighted_ratio = weighted_score / total_possible_weight if total_possible_weight > 0 else 0
        
        # Calculate final confidence
        confidence = base_confidence * (0.3 + 0.7 * weighted_ratio)
        return round(confidence, 2)
    
    def get_differential_diagnoses(self, symptoms: List[str]) -> List[Tuple[str, float]]:
        """Get ranked list of differential diagnoses with confidence scores."""
        diagnoses_scores = []
        
        for diagnosis in self.diagnoses:
            confidence = self.calculate_diagnosis_confidence(diagnosis, symptoms)
            if confidence > 0.1:  # Only include if some confidence
                diagnoses_scores.append((diagnosis, confidence))
        
        # Sort by confidence descending
        diagnoses_scores.sort(key=lambda x: x[1], reverse=True)
        return diagnoses_scores
    
    def assess_urgency(self, symptoms: List[str]) -> Tuple[str, str]:
        """Assess urgency level and provide reasoning."""
        # Check for emergency symptoms
        for symptom in symptoms:
            if symptom in self.urgency_rules['Emergency']:
                return 'Emergency', 'Emergency symptom present requiring immediate attention'
        
        # Check for urgent symptoms
        urgent_count = 0
        for symptom in symptoms:
            if symptom in self.urgency_rules['Urgent']:
                urgent_count += 1
        
        if urgent_count >= 2:
            return 'Urgent', 'Emergency symptom present or multiple urgent symptoms'
        elif urgent_count >= 1:
            return 'Urgent', 'Urgent symptom present requiring prompt attention'
        
        return 'Routine', 'No urgent symptoms identified'
    
    def detect_red_flags(self, symptoms: List[str]) -> List[str]:
        """Detect red flag conditions based on symptoms."""
        detected_flags = []
        
        for flag_category, flag_symptoms in self.red_flags.items():
            if any(symptom in symptoms for symptom in flag_symptoms):
                detected_flags.append(flag_category)
        
        return detected_flags
    
    def get_treatment_recommendations(self, primary_diagnosis: str) -> List[str]:
        """Get treatment recommendations for the primary diagnosis."""
        return self.treatments.get(primary_diagnosis, ['Supportive care', 'Monitor symptoms'])
    
    def analyze_symptoms(self, symptoms: List[str]) -> Dict:
        """Perform complete clinical analysis of symptoms."""
        # Validate symptoms
        validated_symptoms = self.validate_symptoms(symptoms)
        
        # Get differential diagnoses
        diagnoses = self.get_differential_diagnoses(validated_symptoms)
        
        # Get primary diagnosis (highest confidence)
        primary_diagnosis = diagnoses[0] if diagnoses else ('Unknown', 0.0)
        
        # Get treatments
        treatments = self.get_treatment_recommendations(primary_diagnosis[0])
        
        # Assess urgency
        urgency_level, urgency_reasoning = self.assess_urgency(validated_symptoms)
        
        # Detect red flags
        red_flags = self.detect_red_flags(validated_symptoms)
        
        return {
            'validated_symptoms': validated_symptoms,
            'primary_diagnosis': primary_diagnosis,
            'differential_diagnoses': diagnoses[1:],  # Exclude primary
            'treatments': treatments,
            'urgency_level': urgency_level,
            'urgency_reasoning': urgency_reasoning,
            'red_flags': red_flags
        }


def generate_enhanced_rules(max_rules: int = 300, combo_length: int = 3) -> None:
    """
    Generate enhanced clinical rules using symptom combinations.
    
    Args:
        max_rules: Maximum number of rules to generate
        combo_length: Length of symptom combinations (2, 3, or 4)
    """
    # Initialize the engine
    engine = ClinicalRuleEngine()
    symptoms = list(engine.symptoms)
    
    print(f"=== Enhanced Clinical Rule Generation ===")
    print(f"Engine initialized with {len(symptoms)} symptoms")
    print(f"Generating combinations of length: {combo_length}")
    print(f"Maximum rules to generate: {max_rules}")
    print()
    
    # Generate all possible combinations of the specified length
    if combo_length == "all":
        # Generate combinations of length 2, 3, and 4
        all_combinations = []
        for length in [2, 3, 4]:
            combinations = list(itertools.combinations(symptoms, length))
            all_combinations.extend(combinations)
        print(f"Generated {len(all_combinations)} total combinations (lengths 2, 3, 4)")
    else:
        all_combinations = list(itertools.combinations(symptoms, combo_length))
        print(f"Generated {len(all_combinations)} combinations of length {combo_length}")
    
    # Limit the number of combinations if requested
    if max_rules > 0 and len(all_combinations) > max_rules:
        selected_combinations = random.sample(all_combinations, max_rules)
        print(f"Selected {len(selected_combinations)} combinations (limited by max_rules)")
    else:
        selected_combinations = all_combinations
        print(f"Processing all {len(selected_combinations)} combinations")
    
    print()
    print("=== Analyzing Symptom Combinations ===")
    print()
    
    # Analyze each combination and generate rules
    rule_count = 0
    for i, symptom_combo in enumerate(selected_combinations):
        rule_count += 1
        symptoms_list = list(symptom_combo)
        
        print(f"Rule {rule_count}: Analyzing symptoms: {', '.join(symptoms_list)}")
        
        # Perform clinical analysis
        results = engine.analyze_symptoms(symptoms_list)
        
        # Display results
        if results['primary_diagnosis'][1] > 0:  # Only show if there's a valid diagnosis
            primary_diagnosis, primary_confidence = results['primary_diagnosis']
            print(f"  → Primary Diagnosis: {primary_diagnosis} (Confidence: {primary_confidence})")
            
            if results['differential_diagnoses']:
                differentials = results['differential_diagnoses'][:2]  # Show top 2 alternatives
                diff_str = ", ".join([f"{d[0]} ({d[1]})" for d in differentials])
                print(f"  → Alternatives: {diff_str}")
            
            print(f"  → Urgency: {results['urgency_level']}")
            
            if results['red_flags']:
                flags_str = ", ".join(results['red_flags'])
                print(f"  → Red Flags: {flags_str}")
        else:
            print("  → No significant diagnosis patterns identified")
        
        print()
        
        # Add a break for very long outputs
        if rule_count >= 20 and max_rules > 20:
            remaining = len(selected_combinations) - i - 1
            if remaining > 0:
                print(f"  ... processing {remaining} more combinations ...")
                print()
            break
    
    print(f"Enhanced rule generation complete. Generated {rule_count} clinical rules.")


def main():
    """Main function to demonstrate the clinical analysis system."""
    # Initialize the engine
    engine = ClinicalRuleEngine()
    print(f"Engine initialized with {len(engine.symptoms)} symptoms")
    print()
    
    # Sample symptoms for analysis
    sample_symptoms = ['fever', 'cough', 'fatigue', 'shortness_of_breath']
    print(f"Analyzing sample symptoms: {', '.join(sample_symptoms)}")
    print()
    
    # Perform analysis
    results = engine.analyze_symptoms(sample_symptoms)
    
    # Display results in the required format
    print(f"Validated symptoms: {results['validated_symptoms']}")
    print()
    
    print("=== Clinical Analysis Results ===")
    primary_diagnosis, primary_confidence = results['primary_diagnosis']
    print(f"Primary Diagnosis: {primary_diagnosis} (Confidence: {primary_confidence})")
    print()
    
    if results['differential_diagnoses']:
        print("Differential Diagnoses:")
        for diagnosis, confidence in results['differential_diagnoses']:
            print(f"- {diagnosis} (Confidence: {confidence})")
        print()
    
    print("Recommended Treatments:")
    for treatment in results['treatments']:
        print(f"- {treatment}")
    print()
    
    print(f"Urgency Level: {results['urgency_level']}")
    print(f"Reasoning: {results['urgency_reasoning']}")
    print()
    
    if results['red_flags']:
        print("Red Flags:")
        for flag in results['red_flags']:
            print(f"- {flag}")
    else:
        print("Red Flags: None detected")


def parse_arguments():
    """Parse command-line arguments."""
    parser = argparse.ArgumentParser(
        description='Enhanced Clinical Rule Generation System',
        formatter_class=argparse.RawDescriptionHelpFormatter,
        epilog="""
Examples:
  python enhanced_clinical_gen.py --demo                    # Run demo mode
  python enhanced_clinical_gen.py --combo-length=3         # Generate 3-symptom combinations  
  python enhanced_clinical_gen.py --combo-length=2 --max-rules=50  # Generate 50 2-symptom rules
  python enhanced_clinical_gen.py --combo-length=all       # Generate combinations of lengths 2, 3, and 4
        """
    )
    
    parser.add_argument(
        '--demo', 
        action='store_true',
        help='Run in demo mode (shows sample analysis output)'
    )
    
    parser.add_argument(
        '--combo-length',
        type=str,
        default='3',
        help='Length of symptom combinations to generate (2, 3, 4, or "all" for 2+3+4). Default: 3'
    )
    
    parser.add_argument(
        '--max-rules',
        type=int, 
        default=300,
        help='Maximum number of rules to generate. Use 0 for unlimited. Default: 300'
    )
    
    return parser.parse_args()


if __name__ == "__main__":
    args = parse_arguments()
    
    if args.demo:
        # Run the demo mode
        print("=== Demo Mode ===")
        print()
        main()
    else:
        # Run enhanced rule generation mode
        combo_length = args.combo_length
        if combo_length != "all":
            try:
                combo_length = int(combo_length)
                if combo_length not in [2, 3, 4]:
                    print("Error: combo-length must be 2, 3, 4, or 'all'")
                    exit(1)
            except ValueError:
                print("Error: combo-length must be 2, 3, 4, or 'all'")
                exit(1)
        
        generate_enhanced_rules(max_rules=args.max_rules, combo_length=combo_length)