<?php

namespace App\Form;

use App\Entity\Reclamation;
use App\Entity\User;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\TextType ;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
class ReclamationType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
        ->add('username', EntityType::class ,['class'=> User::class,
            'choice_label'=>'nom']) 
    ->add('type', ChoiceType::class, [
        'choices'  => [
            'choisir un type de reclamation' => false,
            'événement' => true,
            'shopping' => true,
            'Autres' => true,],]) 
  ->add('msg' , TextareaType::class, array(
      'label' => 'Description ','attr' => array('placeholder' => 'saisie votre Description de votre reclamation')))
            ->add('dater')
            ->add('add', SubmitType::class ,['label'=>'Valider'])

        ;
      
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Reclamation::class,
        ]);
    }
}
